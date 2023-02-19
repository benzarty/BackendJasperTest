package com.caisse.projet.Controller;

import com.caisse.projet.Model.Client;
import com.caisse.projet.Model.Employee;
import com.caisse.projet.Repository.ClientRepository;
import com.caisse.projet.Service.ClientService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.type.PositionTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.*;


@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private ClientService cliService;

    @Autowired
    ClientRepository repository;


    @GetMapping("/clients")
    public List<Client> list() {
        System.out.println("Get all Clients...");
        return cliService.getAll();
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> post(@PathVariable int id) {
        Optional<Client> four = cliService.findByCode(id);
        return four.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound()
                        .build());
    }

    @PostMapping("/clients")
    public long save(@RequestBody Client Client) {

        return cliService.save(Client);
    }

    @PutMapping("/clients/{code}")
    public void update(@PathVariable int code, @RequestBody Client Client) {
        Optional<Client> four = cliService.findByCode(code);
        if (four.isPresent()) {
            cliService.update(code, Client);
        }
    }

    @DeleteMapping("/clients/{code}")
    public void delete(@PathVariable int code) {
        cliService.delete(code);
    }


    @GetMapping("/test")
    public String generaterepot() throws FileNotFoundException, JRException {
        String path = "C:\\Users\\Morning Beautiful\\Desktop";
        List<Client> clients = repository.findAll();
        File file = ResourceUtils.getFile("classpath:client.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(clients);
        Map<String, Object> parameters = new HashMap<>();
        //parameters.put("firstname","azaza");
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(print, path + "\\client.pdf");
        return "good";

    }


    @GetMapping("/test2")
    public void generateReport() throws JRException {


        String path = "C:\\Users\\Morning Beautiful\\Desktop";

        List<Employee> employees = Arrays.asList(
                new Employee(1, "Ismail Hossain", "TESTTTTTTTTTTTTTT.", 200.00, "abc"),
                new Employee(2, "Shakil", "Software Engg.", 200.00, "abc"),
                new Employee(3, "TQ", "Software Engga.", 200.00, "abc"),
                new Employee(4, "ARUN", "Software Enggfs.", 200.00, "abc"),
                new Employee(5, "Nadif", "Software Enggé.", 200.00, "abc")
        );


        JasperDesign design = getJasperDesign();

        JasperReport jasperReport = JasperCompileManager.compileReport(design);


        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Java World");
        parameters.put("id", "ID");
        parameters.put("name", "NAME");
        parameters.put("designation", "DESIGNATION");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\employees.pdf");


    }




    public JasperDesign getJasperDesign() throws JRException {

        JasperDesign jasDes = new JasperDesign();
        jasDes.setName("myreport");
        jasDes.setPageWidth(595);
        jasDes.setPageHeight(842);
        jasDes.setLeftMargin(0);
        jasDes.setRightMargin(0);
        jasDes.setTopMargin(0);
        jasDes.setBottomMargin(0);
        jasDes.setColumnWidth(555);


        // Style
        JRDesignStyle mystyle = new JRDesignStyle();
        mystyle.setName("mystyle");
        mystyle.setDefault(true);
        mystyle.setFontName("DejaVu Sans");
        mystyle.setFontSize(10f);
        mystyle.setPdfFontName("Helvetica");
        mystyle.setPdfEncoding("UTF-8");


        jasDes.addStyle(mystyle);


        // Fields
        getFields(jasDes);


        // Parameter
        getParameters(jasDes);

//		// Query
//		JRDesignQuery query = new JRDesignQuery();
//		query.setText("SELECT * FROM Cars WHERE Price > $P{CarPrice}");
//		jasDes.setQuery(query);

        // Title
        getTitle(jasDes);

        //COLUMN_HEADER
        getColumnHeaders(jasDes, mystyle);


        // Detail
        getDetails(jasDes, mystyle);


//        // Column footer
//        band = new JRDesignBand();
//        // jasDes.setColumnFooter(band);
//
        getpicture(jasDes);

        // Page footer
        getPageFooter(jasDes, mystyle);

//        // Summary
//        band = new JRDesignBand();
//        // jasDes.setSummary(band);

//        //BACKGROUND:
//        jasDes.setBackground(jrElement);
//
        //NO_DATA:
        getNoData(jasDes, mystyle);

        return jasDes;
    }


    private void getNoData(JasperDesign jasDes, JRDesignStyle mystyle) {
        JRDesignBand noDataBand = new JRDesignBand();
        noDataBand.setHeight(30);


        JRDesignStaticText tfc5 = new JRDesignStaticText();

        tfc5.setX(0);
        tfc5.setY(0);
        tfc5.setWidth(500);
        tfc5.setHeight(30);
        tfc5.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
        tfc5.setStyle(mystyle);
        tfc5.setText("No Data Found");

        noDataBand.addElement(tfc5);

        jasDes.setNoData(noDataBand);
    }

    private void getPageFooter(JasperDesign jasDes, JRDesignStyle mystyle) {
        JRDesignBand pageFooterBand = new JRDesignBand();
        pageFooterBand.setHeight(30);


        JRDesignTextField tfc5 = new JRDesignTextField();
        tfc5.setBlankWhenNull(true);
        tfc5.setX(0);
        tfc5.setY(0);
        tfc5.setWidth(100);
        tfc5.setHeight(30);
        tfc5.setHorizontalTextAlign(HorizontalTextAlignEnum.LEFT);
        tfc5.setStyle(mystyle);
        tfc5.setExpression(new JRDesignExpression("new java.util.Date()"));

        pageFooterBand.addElement(tfc5);


        JRDesignTextField tfc6 = new JRDesignTextField();
        tfc6.setBlankWhenNull(true);
        tfc6.setX(0);
        tfc6.setY(0);
        tfc6.setWidth(500);
        tfc6.setHeight(30);
        tfc6.setHorizontalTextAlign(HorizontalTextAlignEnum.RIGHT);
        tfc6.setStyle(mystyle);
        tfc6.setExpression(new JRDesignExpression("\"Page \"+$V{PAGE_NUMBER}+\" of\"+\" \" + $V{PAGE_NUMBER}"));

        pageFooterBand.addElement(tfc6);


        jasDes.setPageFooter(pageFooterBand);
    }

    private void getDetails(JasperDesign jasDes, JRDesignStyle mystyle) {
        JRDesignBand detailBand = new JRDesignBand();
        detailBand.setHeight(30);


        JRDesignTextField tf1 = new JRDesignTextField();
        tf1.setBlankWhenNull(true);
        tf1.setX(0);
        tf1.setY(0);
        tf1.setWidth(60);
        tf1.setHeight(30);
        tf1.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
        tf1.setStyle(mystyle);
        tf1.setExpression(new JRDesignExpression("$F{id}"));
        tf1.setStretchWithOverflow(true);
        tf1.setMode(ModeEnum.OPAQUE);
        detailBand.addElement(tf1);

        JRDesignTextField tf2 = new JRDesignTextField();
        tf2.setBlankWhenNull(true);
        tf2.setX(100);
        tf2.setY(0);
        tf2.setWidth(200);
        tf2.setHeight(30);
        tf2.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
        tf2.setStyle(mystyle);
        tf2.setExpression(new JRDesignExpression("$F{name}"));
        tf2.setStretchWithOverflow(true);
        tf2.setMode(ModeEnum.OPAQUE);
        detailBand.addElement(tf2);

        JRDesignTextField tf3 = new JRDesignTextField();
        tf3.setBlankWhenNull(true);
        tf3.setX(300);
        tf3.setY(0);
        tf3.setWidth(100);
        tf3.setHeight(30);
        tf3.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
        tf3.setStyle(mystyle);
        tf3.setExpression(new JRDesignExpression("$F{designation}"));
        tf3.setStretchWithOverflow(true);
        tf3.setMode(ModeEnum.OPAQUE);

        detailBand.addElement(tf3);

        ((JRDesignSection) jasDes.getDetailSection()).addBand(detailBand);
    }

    private void getColumnHeaders(JasperDesign jasDes, JRDesignStyle mystyle) {
        JRDesignBand columnHeaderBand = new JRDesignBand();
        columnHeaderBand.setHeight(30);

        JRDesignTextField tfc1 = new JRDesignTextField();
        tfc1.setBlankWhenNull(true);
        tfc1.setX(0);
        tfc1.setY(0);
        tfc1.setWidth(100);
        tfc1.setHeight(30);
        tfc1.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
        tfc1.setStyle(mystyle);
        tfc1.setExpression(new JRDesignExpression("$P{id}"));
        tfc1.setForecolor(new Color(67, 108, 168));
        tfc1.setBackcolor(new Color(.95f, .95f, .95f, 0.5f));
        tfc1.setMode(ModeEnum.OPAQUE);


        columnHeaderBand.addElement(tfc1);


        JRDesignTextField tfc2 = new JRDesignTextField();
        tfc2.setBlankWhenNull(true);
        tfc2.setX(100);
        tfc2.setY(0);
        tfc2.setWidth(200);
        tfc2.setHeight(30);
        tfc2.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
        tfc2.setStyle(mystyle);
        tfc2.setForecolor(new Color(67, 108, 168));
        tfc2.setBackcolor(new Color(230, 230, 230));
        tfc2.setMode(ModeEnum.OPAQUE);
        tfc2.setExpression(new JRDesignExpression("$P{name}"));
        columnHeaderBand.addElement(tfc2);


        JRDesignTextField tfc3 = new JRDesignTextField();
        tfc3.setBlankWhenNull(true);
        tfc3.setX(300);
        tfc3.setY(0);
        tfc3.setWidth(200);
        tfc3.setHeight(30);
        tfc3.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
        tfc3.setStyle(mystyle);
        tfc3.setForecolor(new Color(67, 108, 168));
        tfc3.setBackcolor(new Color(.95f, .95f, .95f, 0.5f));
        tfc3.setMode(ModeEnum.OPAQUE);
        tfc3.setExpression(new JRDesignExpression("$P{designation}"));
        columnHeaderBand.addElement(tfc3);


        JRDesignLine line = new JRDesignLine();
        line.setX(0);
        line.setY(19);
        line.setWidth(515);
        line.setHeight(0);
        line.setForecolor(new Color(67, 108, 168));
        line.setPositionType(PositionTypeEnum.FLOAT);

        columnHeaderBand.addElement(line);

        jasDes.setColumnHeader(columnHeaderBand);
    }

    private void getTitle(JasperDesign jasDes) {
        JRDesignBand titleBand = new JRDesignBand();
        titleBand.setHeight(40);

        JRDesignStaticText titleText = new JRDesignStaticText();
        titleText.setText("Expensive Employees");
        titleText.setX(0);
        titleText.setY(10);
        titleText.setWidth(515);
        titleText.setHeight(30);
        titleText.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
        titleText.setFontSize(22f);
        titleBand.addElement(titleText);
        jasDes.setTitle(titleBand);
    }


    private void getpicture(JasperDesign jasDes) {

        JRDesignImage designImage = new JRDesignImage(jasDes);

// Set the image file
        designImage.setExpression(new JRDesignExpression("\"/C:/Users/Morning Beautiful/Desktop/tt.jpeg\""));

// Set the image dimensions and position
        designImage.setX(0);
        designImage.setY(0);
        designImage.setWidth(100);
        designImage.setHeight(100);


// Create a new band to hold the image
        JRDesignBand band = new JRDesignBand();
        band.setHeight(100);

// Add the image to the band
        band.addElement(designImage);

// Set the band as the title band of the report
        jasDes.setTitle(band);


    }

    @GetMapping("/test3")
    public void generateChart(JasperDesign jasDes) throws JRException {




    }

    private void getParameters(JasperDesign jasDes) throws JRException {
        JRDesignParameter par = new JRDesignParameter();
        par.setName("createdBy");
        par.setValueClass(String.class);
        jasDes.addParameter(par);


        JRDesignParameter par2 = new JRDesignParameter();
        par2.setName("id");
        par2.setValueClass(String.class);
        jasDes.addParameter(par2);


        JRDesignParameter par3 = new JRDesignParameter();
        par3.setName("name");
        par3.setValueClass(String.class);
        jasDes.addParameter(par3);


        JRDesignParameter par4 = new JRDesignParameter();
        par4.setName("designation");
        par4.setValueClass(String.class);
        jasDes.addParameter(par4);

    }

    private void getFields(JasperDesign jasDes) throws JRException {

        JRDesignField field1 = new JRDesignField();
        field1.setName("id");
        field1.setValueClass(Integer.class);
        jasDes.addField(field1);

        JRDesignField field2 = new JRDesignField();
        field2.setName("name");
        field2.setValueClass(String.class);
        jasDes.addField(field2);

        JRDesignField field3 = new JRDesignField();
        field3.setName("designation");
        field3.setValueClass(String.class);
        jasDes.addField(field3);


    }




}







