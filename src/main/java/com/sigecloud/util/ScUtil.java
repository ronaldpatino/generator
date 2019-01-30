package com.sigecloud.util;

import com.google.gson.Gson;
import com.sigecloud.pojo.Widget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class ScUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScUtil.class);

    public static String CSS_TEMPLATE_FILE = "crudTemplates/css.vm";
    public static String FXML_TEMPLATE_FILE = "crudTemplates/fxml.vm";
    public static String PRESENTER_TEMPLATE_FILE = "crudTemplates/presenter.vm";
    public static String VIEW_TEMPLATE_FILE = "crudTemplates/view.vm";
    public static String SERVICE_TEMPLATE_FILE = "crudTemplates/service.vm";
    public static String EDIT_CHECK_TEMPLATE_FILE = "crudTemplates/editCheck.vm";
    public static String INSERT_CHECK_TEMPLATE_FILE = "crudTemplates/insertCheck.vm";
    public static String DASHBOARD_PRESENTER_TEMPLATE_FILE = "crudTemplates/dashboardPresenter.vm";
    public static String DASHBOARD_FXML_TEMPLATE_FILE = "crudTemplates/dashboardFxml.vm";
    public static String MAIN_TEMPLATE_FILE = "crudTemplates/main.vm";

    public static String GENERIC_DAO_IMPL_TEMPLATE_FILE = "daoTemplates/GenericDAOImpl.vm";
    public static String GENERIC_SERVICE_IMPL_TEMPLATE_FILE = "daoTemplates/GenericServiceImpl.vm";
    public static String IGENERIC_DAO_TEMPLATE_FILE = "daoTemplates/IGenericDAO.vm";
    public static String IGENERIC_SERVICE_TEMPLATE_FILE = "daoTemplates/IGenericService.vm";

    public static String  IPAGER_TEMPLATE_FILE = "pagerTemplates/IPager.vm";
    public static String  PAGER_TEMPLATE_FILE = "pagerTemplates/Pager.vm";

    public static String  HIBERNATE_TEMPLATE_FILE = "hibernateTemplates/hibernate.cfg.vm";

    public static String  MAVEN_TEMPLATE_FILE = "mavenTemplates/pom.vm";

    public static String  DOMAIN_TEMPLATE_FILE = "domainTemplates/domain.vm";

    public static String  CONFIG_TEMPLATE_FILE = "utilTemplates/config.vm";
    public static String  FXUTIL_TEMPLATE_FILE = "utilTemplates/fxUtil.vm";
    public static String  HIBERNATE_UTILS_TEMPLATE_FILE = "utilTemplates/hibernateUtils.vm";
    public static String  SCUTIL_TEMPLATE_FILE = "utilTemplates/scUtil.vm";
    public static String  CONFIG_PROPERTIES_TEMPLATE_FILE = "configTemplates/configProperties.vm";



    public static String DAO_DIR = "crudTemplates/daoTemplates";
    public static String UTIL_PACKAGE = "util";
    public static String PAGER_PACKAGE = "pager";
    public static String DAO_PACKAGE = "dao";
    public static String DAO_GENERIC = "generic";
    public static String DAO_SERVICE = "service";
    public static String GENERIC_DAO_IMPL = "GenericDAOImpl";
    public static String IGENERIC_DAO = "IGenericDAO";
    public static String GENERIC_SERVICE_IMPL = "GenericServiceImpl";
    public static String IGENERIC_SERVICE = "IGenericService";
    public static String IPAGER = "IPager";
    public static String PAGER = "Pager";
    public static String HIBERNATE = "hibernate.cfg";
    public static String POM = "pom";
    public static String GENERATED = "generated";
    public static String MAIN = "main";
    public static String JAVA = "java";


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


    public static String CONFIG = "config";
    public static String DASHBOARD = "dashboard";
    public static String APP = "app";
    public static String DASHBOARD_CLASSNAME = "Dashboard";
    public static String CONFIG_CLASSNAME = "Config";
    public static String FXUTIL_CLASSNAME = "FxUtil";
    public static String HIBERNATEUTIL_CLASSNAME = "HibernateUtils";
    public static String SCUTIL_CLASSNAME = "ScUtil";
    public static String DASHBOARD_CLASSNAME_INSTANCE = "dashboard";
    public static String DOT = ".";
    public static String PRESENTER = "Presenter";
    public static String VIEW = "View";
    public static String SERVICE = "Service";
    public static String MENU = "menu";
    public static String UTIL = "util";

    public static String DOT_JAVA = ".java";
    public static String DOT_CSS = ".css";
    public static String DOT_FXML = ".fxml";
    public static String DOT_VM = ".vm";
    public static String DOT_XML = ".xml";
    public static String DOT_PROPERTIES = ".properties";



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

    public static  void writeConfig(StringWriter contentToWrite) {

        String fileToSave = HOME_SAVE_PATH + CONFIG + DOT_PROPERTIES;

        try {

            FileWriter fw = new FileWriter(fileToSave);
            fw.write(contentToWrite.toString());
            fw.close();

        } catch (IOException e) {
            LOGGER.error("ERROR - Could not save file {}", fileToSave);
            LOGGER.error(e.toString());
        }
    }



    public static  void writeToFile(String path, String fileToWrite, StringWriter contentToWrite) {

        String fileToSave = path + File.separator +  fileToWrite;

        try {

            new File(path + File.separator).mkdirs();
            FileWriter fw = new FileWriter(fileToSave);
            fw.write(contentToWrite.toString());
            fw.close();

        } catch (IOException e) {
            LOGGER.error("ERROR - Could not save file {}", fileToSave);
            LOGGER.error(e.toString());
        }
    }
}

