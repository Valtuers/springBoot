package com.lmc.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import com.lmc.bean.User;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import com.itextpdf.text.pdf.PdfPTable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class FileController {

    @RequestMapping("/pdf")
    public void exportPdf(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/pdf");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=mapper.pdf");
        //模拟用户数据
        List<User> users = new ArrayList<>();
        users.add(new User(){{
            setId(1);
            setUsername("张一");
            setPassword("111");
        }});
        users.add(new User(){{
            setId(2);
            setUsername("zhanger");
            setPassword("222");
        }});
        users.add(new User(){{
            setId(3);
            setUsername("zhangsan");
            setPassword("333");
        }});
        Rectangle pageSize = new Rectangle(300, 500);
        pageSize.setBackgroundColor(new BaseColor(0xFF, 0xFF, 0xDE));
        Document document = new Document(pageSize);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        for (User user : users) {
            PdfPTable table = new PdfPTable(3);
            PdfPCell cell = new PdfPCell();
            cell.setPhrase(new Paragraph(user.getId()));
            table.addCell(cell);
            document.add(table);

            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(user.getUsername()));
            table.addCell(cell);
            document.add(table);

            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(user.getPassword()));
            table.addCell(cell);
            document.add(table);
        }
        document.close();
    }

    //-------------------------------------------------------------------------
    //导出excel
    @RequestMapping("excel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        //表头数据
        String[] header = {"ID", "姓名", "性别", "年龄", "地址", "分数"};

        //数据内容
        String[] student1 = {"1", "小红", "女", "23", "广州海珠区", "96"};
        String[] student2 = {"2", "小强", "男", "26", "广州天河区", "91"};
        String[] student3 = {"3", "小明", "男", "28", "广州越秀区", "90"};

        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        //生成一个表格，设置表格名称为"学生表"
        HSSFSheet sheet = workbook.createSheet("学生表");

        //设置表格列宽度为10个字节
        sheet.setDefaultColumnWidth(10);

        //创建第一行表头
        HSSFRow headrow = sheet.createRow(0);

        //遍历添加表头(下面模拟遍历学生，也是同样的操作过程)
        for (int i = 0; i < header.length; i++) {
            //创建一个单元格
            HSSFCell cell = headrow.createCell(i);

            //创建一个内容对象
            HSSFRichTextString text = new HSSFRichTextString(header[i]);

            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(text);
        }

        //模拟遍历结果集，把内容加入表格
        //模拟遍历第一个学生
        HSSFRow row1 = sheet.createRow(1);
        for (int i = 0; i < student1.length; i++) {
            HSSFCell cell = row1.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(student1[i]);
            cell.setCellValue(text);
        }

        //模拟遍历第二个学生
        HSSFRow row2 = sheet.createRow(2);
        for (int i = 0; i < student2.length; i++) {
            HSSFCell cell = row2.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(student2[i]);
            cell.setCellValue(text);
        }

        //模拟遍历第三个学生
        HSSFRow row3 = sheet.createRow(3);
        for (int i = 0; i < student3.length; i++) {
            HSSFCell cell = row3.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(student3[i]);
            cell.setCellValue(text);
        }

        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/octet-stream");

        //这后面可以设置导出Excel的名称，此例中名为student.xlsx
        response.setHeader("Content-disposition", "attachment;filename=student.xlsx");

        //刷新缓冲
        response.flushBuffer();

        //workbook将Excel写入到response的输出流中，供页面下载
        workbook.write(response.getOutputStream());
    }

    //-------------------------------------------------------------------------
    //上传表单提交文件
    @RequestMapping("/uploadFile")
    @ResponseBody
    public String uploadFile(MultipartFile file){
        String filename = file.getOriginalFilename();
        File dest = new File(filename);
        try{
            file.transferTo(dest);
        }catch (Exception e){
            e.printStackTrace();
            return "上传失败！";
        }
        return "上传成功！";
    }
    //上传base64文件
    @RequestMapping("/uploadFileBase64")
    @ResponseBody
    public String uploadFileBase64(String base64Data){
        String dataPrix = ""; //base64格式前头
        String data = "";//实体部分数据
        if(base64Data==null||"".equals(base64Data)){
            return "上传失败，上传图片数据为空";
        }else {
            String [] d = base64Data.split("base64,");//将字符串分成数组
            if(d != null && d.length == 2){
                dataPrix = d[0];
                //这个replaceAll是关键，上网找了很久才找到
                data = d[1].replaceAll(" ","+");
            }else {
                return "上传失败，数据不合法";
            }
        }
        String suffix = "";//图片后缀，用以识别哪种格式数据
        //data:image/jpeg;base64,base64编码的jpeg图片数据
        if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){
            suffix = ".jpg";
        }else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){
            //data:image/x-icon;base64,base64编码的icon图片数据
            suffix = ".ico";
        }else if("data:image/gif;".equalsIgnoreCase(dataPrix)){
            //data:image/gif;base64,base64编码的gif图片数据
            suffix = ".gif";
        }else if("data:image/png;".equalsIgnoreCase(dataPrix)){
            //data:image/png;base64,base64编码的png图片数据
            suffix = ".png";
        }else {
            return "上传图片格式不合法";
        }
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String tempFileName=uuid+suffix;
        String imgFilePath = "D:\\IntelliJ IDEA\\project\\springBoot\\springBoot-10-file\\src" +
                "\\main\\resources\\static\\"+tempFileName;
        //新生成的图片
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(data);
            for(int i=0;i<b.length;++i) {
                if(b[i]<0) {
                    //调整异常数据
                    b[i]+=256;
                }
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return "200";
        } catch (IOException e) {
            e.printStackTrace();
            return "上传图片失败";
        }
    }

}