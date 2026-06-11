package com.heima.ymhaicodermother.core;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.heima.ymhaicodermother.ai.model.HtmlCodeResult;
import com.heima.ymhaicodermother.ai.model.MultiFileCodeResult;
import com.heima.ymhaicodermother.model.enums.CodeGenTypeEnum;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * 文件保存器
 */
@Deprecated
public class CodeFileSaver {

    //文件保存的根目录
    private static final String FILE_SAVE_ROOT_DIR=System.getProperty("user.dir")+"/tmp/code_output";
    //保存HTML网页代码
    public static File saveHtmlCodeResult(HtmlCodeResult htmlCodeResult){
        String baseDirPath=buildUniqueDir(CodeGenTypeEnum.HTML.getValue());
        writeToFile(baseDirPath,"index.html",htmlCodeResult.getHtmlCode());
        return new File(baseDirPath);
    }

    /**
     * 保存 MultiFileCodeResult
     */
    public static File saveMultiFileCodeResult(MultiFileCodeResult result) {
        String baseDirPath = buildUniqueDir(CodeGenTypeEnum.MULTI_FILE.getValue());
        writeToFile(baseDirPath, "index.html", result.getHtmlCode());
        writeToFile(baseDirPath, "style.css", result.getCssCode());
        writeToFile(baseDirPath, "script.js", result.getJsCode());
        return new File(baseDirPath);
    }

    //构建文件的唯一路径（tmp/code_output/bizType_雪花 ID）
    private static String buildUniqueDir(String bizType){
        String uniqueDirName= StrUtil.format("{}_{}",bizType,IdUtil.getSnowflakeNextIdStr());
        String dirPath=FILE_SAVE_ROOT_DIR+ File.separator+uniqueDirName;
        FileUtil.mkdir(dirPath);
        return dirPath;
    }
    //保存单个文件
    private static void writeToFile(String dirPath,String fileName,String content){
        String filePath=dirPath+ File.separator+fileName;
        FileUtil.writeString(content,filePath, StandardCharsets.UTF_8);
    }
}
