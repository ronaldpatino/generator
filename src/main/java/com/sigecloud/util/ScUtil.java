package com.sigecloud.util;

import com.google.gson.Gson;
import com.sigecloud.pojo.Widget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class ScUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScUtil.class);

    public static String CSS_TEMPLATE_FILE = "templates/css.vm";
    public static String FXML_TEMPLATE_FILE = "templates/fxml.vm";
    public static String PRESENTER_TEMPLATE_FILE = "templates/presenter.vm";
    public static String VIEW_TEMPLATE_FILE = "templates/view.vm";
    public static String SERVICE_TEMPLATE_FILE = "templates/service.vm";
    public static String EDIT_CHECK_TEMPLATE_FILE = "templates/editCheck.vm";
    public static String INSERT_CHECK_TEMPLATE_FILE = "templates/insertCheck.vm";
    public static String DASHBOARD_PRESENTER_TEMPLATE_FILE = "templates/dashboardPresenter.vm";
    public static String DASHBOARD_FXML_TEMPLATE_FILE = "templates/dashboardFxml.vm";
    public static String MAIN_TEMPLATE_FILE = "templates/main.vm";

    public static String GENERIC_DAO_IMPL_TEMPLATE_FILE = "daoTemplates/GenericDAOImpl.vm";
    public static String GENERIC_SERVICE_IMPL_TEMPLATE_FILE = "daoTemplates/GenericServiceImpl.vm";
    public static String IGENERIC_DAO_TEMPLATE_FILE = "daoTemplates/IGenericDAO.vm";
    public static String IGENERIC_SERVICE_TEMPLATE_FILE = "daoTemplates/IGenericService.vm";

    public static String  IPAGER_TEMPLATE_FILE = "pagerTemplates/IPager.vm";
    public static String  PAGER_TEMPLATE_FILE = "pagerTemplates/Pager.vm";

    public static String  HIBERNATE_TEMPLATE_FILE = "hibernateTemplates/hibernate.cfg.vm";

    public static String  MAVEN_TEMPLATE_FILE = "mavenTemplates/pom.vm";



    public static String DAO_DIR = "templates/daoTemplates";
    public static String UTIL_PACKAGE = "util";
    public static String PAGER_PACKAGE = "pager";
    public static String DAO_PACKAGE = "dao";
    public static String DAO_GENERIC = "generic";
    public static String DAO_SERVICE = "service";
    public static String GENERIC_DAO_IMPL = "GenericDAOImpl";
    public static String IGENERIC_DAO = "IGenericDAOImpl";
    public static String GENERIC_SERVICE_IMPL = "GenericServiceImpl";
    public static String IGENERIC_SERVICE = "IGenericServiceImpl";
    public static String IPAGER = "IPager";
    public static String PAGER = "Pager";
    public static String HIBERNATE = "hibernate.cfg";
    public static String POM = "pom";


    public static String SAVE_PATH = System.getProperty("user.dir") +
            File.separator +
            "generated" +
            File.separator +
            "main" +
            File.separator +
            "java" +
            File.separator;

    public static String RESOURCE_SAVE_PATH = System.getProperty("user.dir") +
            File.separator +
            "generated" +
            File.separator +
            "main" +
            File.separator +
            "resource" +
            File.separator;


    public static String HOME_SAVE_PATH = System.getProperty("user.dir") +
            File.separator +
            "generated" +
            File.separator;


    public static String DASHBOARD = "dashboard";
    public static String APP = "app";
    public static String DASHBOARD_CLASSNAME = "Dashboard";
    public static String DASHBOARD_CLASSNAME_INSTANCE = "dashboard";
    public static String DOT = ".";
    public static String PRESENTER = "Presenter";
    public static String VIEW = "View";
    public static String SERVICE = "Service";
    public static String MENU = "menu";

    public static String DOT_JAVA = ".java";
    public static String DOT_CSS = ".css";
    public static String DOT_FXML = ".fxml";
    public static String DOT_VM = ".vm";
    public static String DOT_XML = ".xml";



    public static Widget loadJsonFile(String jsonFile){
        Gson gson = new Gson();
        Widget widget = null;
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("test.json"));

            widget = gson.fromJson(br, Widget.class);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return widget;
    }

    public static  void writeToFile(String fileToWrite, StringWriter contentToWrite, String packagePath) {

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


    public static  void writeToResource(String fileToWrite, StringWriter contentToWrite ) {

        String fileToSave = RESOURCE_SAVE_PATH +
                fileToWrite;
        try {

            new File(RESOURCE_SAVE_PATH ).mkdirs();

            FileWriter fw = new FileWriter(fileToSave);
            fw.write(contentToWrite.toString());
            fw.close();

        } catch (IOException e) {
            LOGGER.error("ERROR - Could not save file {}", fileToSave);
            LOGGER.error(e.toString());
        }
    }

    public static  void writeMaven(StringWriter contentToWrite) {

        String fileToSave = HOME_SAVE_PATH +
                POM +
                DOT_XML;
        try {

            FileWriter fw = new FileWriter(fileToSave);
            fw.write(contentToWrite.toString());
            fw.close();

        } catch (IOException e) {
            LOGGER.error("ERROR - Could not save file {}", fileToSave);
            LOGGER.error(e.toString());
        }
    }

}

