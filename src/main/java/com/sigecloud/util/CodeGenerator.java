package com.sigecloud.util;

import com.sigecloud.pojo.Widget;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

public class CodeGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeGenerator.class);
    private static String SAVE_PATH = System.getProperty("user.dir") + File.separator + "generated" + File.separator;
    private static String JAVA_PACKAGE_PATH;
    private static String VALIDATOR_JAVA_PACKAGE_PATH;

    private static String CSS_TEMPLATE_FILE = "templates/css.vm";
    private static String FXML_TEMPLATE_FILE = "templates/fxml.vm";
    private static String PRESENTER_TEMPLATE_FILE = "templates/presenter.vm";
    private static String VIEW_TEMPLATE_FILE = "templates/view.vm";
    private static String SERVICE_TEMPLATE_FILE = "templates/service.vm";
    private static String EDIT_CHECK_TEMPLATE_FILE = "templates/editCheck.vm";
    private static String INSERT_CHECK_TEMPLATE_FILE = "templates/insertCheck.vm";

    private VelocityEngine velocityEngine;
    private VelocityContext context;
    private Template cssTemplate;
    private Template fxmlTemplate;
    private Template presenterTemplate;
    private Template viewTemplate;
    private Template serviceTemplate;
    private Template editCheckTemplate;
    private Template insertCheckTemplate;
    private Widget w;

    public CodeGenerator(Widget widget) {

        w = widget;
        Properties p = new Properties();
        p.setProperty("resource.loader", "class");
        p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        context = new VelocityContext();
        velocityEngine = new VelocityEngine();
        velocityEngine.init(p);

        cssTemplate  = velocityEngine.getTemplate(CSS_TEMPLATE_FILE);
        fxmlTemplate = velocityEngine.getTemplate(FXML_TEMPLATE_FILE);
        presenterTemplate = velocityEngine.getTemplate(PRESENTER_TEMPLATE_FILE);
        viewTemplate  = velocityEngine.getTemplate(VIEW_TEMPLATE_FILE);
        serviceTemplate  = velocityEngine.getTemplate(SERVICE_TEMPLATE_FILE);
        editCheckTemplate = velocityEngine.getTemplate(EDIT_CHECK_TEMPLATE_FILE);
        insertCheckTemplate = velocityEngine.getTemplate(INSERT_CHECK_TEMPLATE_FILE);

        JAVA_PACKAGE_PATH = widget.getJavaPackage().replace(".","/") + File.separator;
        VALIDATOR_JAVA_PACKAGE_PATH = widget.getValidatorJavaPackage().replace(".","/") + File.separator;

    }

    public void generate(){

        context.put("javaPackage", w.getJavaPackage());
        context.put("validatorJavaPackage", w.getValidatorJavaPackage());
        context.put("className", w.getClassName());
        context.put("classNameInstance", w.getClassNameInstance());
        context.put("objectName", w.getObjectName());
        context.put("sortBy", w.getSortByField());
        context.put("findBy", w.getSortByField());
        context.put("fields", w.getFields());



        StringWriter css = new StringWriter();
        cssTemplate.merge( context, css );

        StringWriter fxml = new StringWriter();
        fxmlTemplate.merge( context, fxml );


        StringWriter presenter = new StringWriter();
        presenterTemplate.merge( context, presenter );

        StringWriter view = new StringWriter();
        viewTemplate.merge( context, view );


        StringWriter service = new StringWriter();
        serviceTemplate.merge( context, service );

        StringWriter editCheck = new StringWriter();
        editCheckTemplate.merge( context, editCheck );

        StringWriter insertCheck = new StringWriter();
        insertCheckTemplate.merge( context, insertCheck );

        writeToFile(w.getClassName().toLowerCase() + ".css", css, JAVA_PACKAGE_PATH);
        writeToFile(w.getClassName() + ".fxml", fxml, JAVA_PACKAGE_PATH);
        writeToFile(w.getClassName() + "Presenter.java", presenter, JAVA_PACKAGE_PATH);
        writeToFile(w.getClassName() + "View.java", view, JAVA_PACKAGE_PATH);
        writeToFile(w.getClassName() + "Service.java", service, JAVA_PACKAGE_PATH);
        writeToFile(w.getClassName() + "EditCheck.java", editCheck, VALIDATOR_JAVA_PACKAGE_PATH);
        writeToFile(w.getClassName() + "InsertCheck.java", insertCheck, VALIDATOR_JAVA_PACKAGE_PATH);


    }

    private void writeToFile(String fileToWrite, StringWriter contentToWrite, String packagePath){

        String fileToSave = SAVE_PATH +
                packagePath +
                File.separator +
                fileToWrite;
        try {

            new File(SAVE_PATH +
                    packagePath)
                    .mkdirs();

            FileWriter fw = new FileWriter(fileToSave);
            fw.write(contentToWrite.toString());
            fw.close();

        } catch (IOException e) {
            LOGGER.error("ERROR - Could not save file {}", fileToSave);
            LOGGER.error(e.toString());
        }
    }



}
