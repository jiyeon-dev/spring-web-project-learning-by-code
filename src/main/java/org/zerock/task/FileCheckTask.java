package org.zerock.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.zerock.domain.BoardAttachVO;
import org.zerock.mapper.BoardAttachMapper;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileCheckTask {

    private static final Logger log = LoggerFactory.getLogger(FileCheckTask.class);

    private BoardAttachMapper attachMapper;

    private String getFolderYesterDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);  // 어제
        String str = sdf.format(cal.getTime());

        return str.replace("-", File.separator);
    }

    @Scheduled(cron = "0 0 2 * * *")  // 매일 새벽 2시
    public void checkFiles() throws Exception {
        log.warn("File Check Task run .............");
        log.warn("" + new Date());

        // 데이터베이스로부터 어제 첨부파일 리스트 조회
        List<BoardAttachVO> fileList = attachMapper.getOldFiles();

        // 파일 경로 fileListPaths 에 추가
        List<Path> fileListPaths = fileList.stream()
                .map(vo -> Paths.get("C:\\upload", vo.getUploadPath(), vo.getUuid() + "_" + vo.getFileName()))
                .collect(Collectors.toList());

        // 이미지인 경우 썸네일 경로 fileListPaths 에 추가
        fileList.stream().filter(vo -> vo.isFileType() == true)
                .map(vo -> Paths.get("C:\\upload", vo.getUploadPath(), "s_" + vo.getUuid() + "_" + vo.getFileName()))
                .forEach(p -> fileListPaths.add(p));

        log.warn("========================================");

        fileListPaths.forEach(p -> log.warn("" + p));

        // 어제 날짜 폴더
        File targetDir = Paths.get("C:\\upload", getFolderYesterDay()).toFile();

        // 삭제할 파일 리스트 : 어제 날짜 폴더에 파일이 fileListPaths 에 없는 경우 removeFiles 에 추가
        File[] removeFiles = targetDir.listFiles(file -> fileListPaths.contains(file.toPath()) == false);

        log.warn("----------------------------------------");

        // 파일 삭제
        for (File file: removeFiles) {
            log.warn(file.getAbsolutePath());
            file.delete();
        }
    }
}
