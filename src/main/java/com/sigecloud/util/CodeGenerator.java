package com.sigecloud.util;

import com.sigecloud.pojo.Widget;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringWriter;
import java.util.Properties;

public class CodeGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeGenerator.class);

    private static String JAVA_PACKAGE_PATH;
    private static String VALIDATOR_JAVA_PACKAGE_PATH;
    private static String UTIL_JAVA_PACKAGE_PATH;
    private static String MAIN_JAVA_PACKAGE_PATH;
    private static String DASHBOARD_JAVA_PACKAGE_PATH;
    private static String DAO_JAVA_PACKAGE_PATH;


    private VelocityEngine velocityEngine;
    private VelocityContext context;
    private VelocityContext dashboardContex;
    private VelocityContext daoContex;
    private VelocityContext pagerContex;
    private Template cssTemplate;
    private Template fxmlTemplate;
    private Template presenterTemplate;
    private Template viewTemplate;
    private Template serviceTemplate;
    private Template editCheckTemplate;
    private Template insertCheckTemplate;
    private Template mainTemplate;
    private Template dashboardPresenterTemplate;
    private Template dashboardFxmlTemplate;
    private Template genericDaoImpTemplate;
    private Template iGenericDaoTemplate;
    private Template genericServiceImpTemplate;
    private Template iGenericServiceTemplate;
    private Template pagerTemplate;
    private Template iPagerTemplate;

    private Widget w;

    public CodeGenerator(Widget widget) {

        w = widget;
        Properties p = new Properties();
        p.setProperty("resource.loader", "class");
        p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        context = new VelocityContext();
        velocityEngine = new VelocityEngine();
        velocityEngine.init(p);

        cssTemplate = velocityEngine.getTemplate(ScUtil.CSS_TEMPLATE_FILE);
        fxmlTemplate = velocityEngine.getTemplate(ScUtil.FXML_TEMPLATE_FILE);
        presenterTemplate = velocityEngine.getTemplate(ScUtil.PRESENTER_TEMPLATE_FILE);
        viewTemplate = velocityEngine.getTemplate(ScUtil.VIEW_TEMPLATE_FILE);
        serviceTemplate = velocityEngine.getTemplate(ScUtil.SERVICE_TEMPLATE_FILE);
        editCheckTemplate = velocityEngine.getTemplate(ScUtil.EDIT_CHECK_TEMPLATE_FILE);
        insertCheckTemplate = velocityEngine.getTemplate(ScUtil.INSERT_CHECK_TEMPLATE_FILE);
        dashboardPresenterTemplate = velocityEngine.getTemplate(ScUtil.DASHBOARD_PRESENTER_TEMPLATE_FILE);
        dashboardFxmlTemplate = velocityEngine.getTemplate(ScUtil.DASHBOARD_FXML_TEMPLATE_FILE);
        mainTemplate = velocityEngine.getTemplate(ScUtil.MAIN_TEMPLATE_FILE);
        pagerTemplate = velocityEngine.getTemplate(ScUtil.PAGER_TEMPLATE_FILE);
        iPagerTemplate = velocityEngine.getTemplate(ScUtil.IPAGER_TEMPLATE_FILE);

        JAVA_PACKAGE_PATH = widget.getJavaPackage().replace(ScUtil.DOT, File.separator) +
                File.separator;

        VALIDATOR_JAVA_PACKAGE_PATH = widget.getValidatorJavaPackage().replace(ScUtil.DOT, File.separator) +
                File.separator;

        UTIL_JAVA_PACKAGE_PATH = widget.getUtilJavaPackage().replace(ScUtil.DOT, File.separator) +
                File.separator;

        MAIN_JAVA_PACKAGE_PATH = widget.getMainJavaPackage().replace(ScUtil.DOT, File.separator) +
                File.separator;

        DASHBOARD_JAVA_PACKAGE_PATH = widget.getRootJavaPackage().replace(ScUtil.DOT, File.separator) +
                File.separator +
                ScUtil.APP +
                File.separator +
                ScUtil.DASHBOARD +
                File.separator;

        DAO_JAVA_PACKAGE_PATH = widget.getRootJavaPackage().replace(ScUtil.DOT, File.separator) +
                File.separator +
                ScUtil.DAO_PACKAGE +
                File.separator;


        genericDaoImpTemplate = velocityEngine.getTemplate(ScUtil.GENERIC_DAO_IMPL_TEMPLATE_FILE);
        iGenericDaoTemplate = velocityEngine.getTemplate(ScUtil.IGENERIC_DAO_TEMPLATE_FILE);
        genericServiceImpTemplate  = velocityEngine.getTemplate(ScUtil.GENERIC_SERVICE_IMPL_TEMPLATE_FILE);
        iGenericServiceTemplate = velocityEngine.getTemplate(ScUtil.IGENERIC_SERVICE_TEMPLATE_FILE);

    }

    public void generate() {

        context.put("mainJavaPackage", w.getMainJavaPackage());
        context.put("javaPackage", w.getJavaPackage());
        context.put("validatorJavaPackage", w.getValidatorJavaPackage());
        context.put("className", w.getClassName());
        context.put("classNameInstance", w.getClassNameInstance());
        context.put("objectName", w.getObjectName());
        context.put("sortBy", w.getSortByField());
        context.put("findBy", w.getSortByField());
        context.put("fields", w.getFields());


        StringWriter css = new StringWriter();
        cssTemplate.merge(context, css);

        StringWriter fxml = new StringWriter();
        fxmlTemplate.merge(context, fxml);

        StringWriter presenter = new StringWriter();
        presenterTemplate.merge(context, presenter);

        StringWriter view = new StringWriter();
        viewTemplate.merge(context, view);

        StringWriter service = new StringWriter();
        serviceTemplate.merge(context, service);

        StringWriter editCheck = new StringWriter();
        editCheckTemplate.merge(context, editCheck);

        StringWriter insertCheck = new StringWriter();
        insertCheckTemplate.merge(context, insertCheck);

        StringWriter mainJavaApp = new StringWriter();
        mainTemplate.merge(context, mainJavaApp);

        ScUtil.writeToFile(w.getClassName().toLowerCase() + ".css", css, JAVA_PACKAGE_PATH);
        ScUtil.writeToFile(w.getClassName() + ScUtil.DOT_FXML, fxml, JAVA_PACKAGE_PATH);
        ScUtil.writeToFile(w.getClassName() + ScUtil.PRESENTER +
                ScUtil.DOT_JAVA, presenter,
                JAVA_PACKAGE_PATH);

        ScUtil.writeToFile(w.getClassName() +
                ScUtil.VIEW + ScUtil.DOT_JAVA, view,
                JAVA_PACKAGE_PATH);

        ScUtil.writeToFile(w.getClassName() +
                ScUtil.SERVICE +
                ScUtil.DOT_JAVA ,
                service,
                JAVA_PACKAGE_PATH);

        ScUtil.writeToFile(w.getClassName() + "EditCheck" +
                ScUtil.DOT_JAVA,
                editCheck,
                VALIDATOR_JAVA_PACKAGE_PATH);

        ScUtil.writeToFile(w.getClassName() + "InsertCheck"  +
                ScUtil.DOT_JAVA,
                insertCheck,
                VALIDATOR_JAVA_PACKAGE_PATH);

        ScUtil.writeToFile("App"  +
                ScUtil.DOT_JAVA,
                mainJavaApp,
                MAIN_JAVA_PACKAGE_PATH);


        /*Generating dashboard*/
        dashboardContex = new VelocityContext();

        dashboardContex.put("mainJavaPackage", w.getRootJavaPackage());
        dashboardContex.put("rootJavaPackage", w.getRootJavaPackage());
        dashboardContex.put("appPackage", ScUtil.APP);
        dashboardContex.put("dashboardPackage", ScUtil.DASHBOARD);
        dashboardContex.put("className", ScUtil.DASHBOARD_CLASSNAME);
        dashboardContex.put("classNameInstance", ScUtil.DASHBOARD_CLASSNAME_INSTANCE);
        dashboardContex.put("objectName", w.getObjectName());
        dashboardContex.put("javaPackage", w.getRootJavaPackage() +
                ScUtil.DOT +
                ScUtil.APP +
                ScUtil.DOT +
                ScUtil.DASHBOARD);

        dashboardContex.put("fxController", w.getRootJavaPackage() +
                ScUtil.DOT +
                ScUtil.APP +
                ScUtil.DOT +
                ScUtil.DASHBOARD_CLASSNAME_INSTANCE +
                ScUtil.DOT +
                ScUtil.DASHBOARD_CLASSNAME +
                ScUtil.PRESENTER);

        dashboardContex.put("onAction", ScUtil.MENU + w.getClassName());
        dashboardContex.put("menuText", w.getClassName());
        dashboardContex.put("menuImportPackage", w.getJavaPackage());
        dashboardContex.put("className", w.getClassName());
        dashboardContex.put("classNameInstance", w.getClassNameInstance());



        StringWriter dashboardPresenter = new StringWriter();
        dashboardPresenterTemplate.merge(dashboardContex, dashboardPresenter);

        StringWriter dashboardView = new StringWriter();
        viewTemplate.merge(dashboardContex, dashboardView);

        StringWriter dashboardCss = new StringWriter();
        viewTemplate.merge(dashboardContex, dashboardCss);

        StringWriter dashboardFxml = new StringWriter();
        dashboardFxmlTemplate.merge(dashboardContex, dashboardFxml);


        ScUtil.writeToFile(ScUtil.DASHBOARD_CLASSNAME.toLowerCase() +
                ScUtil.DOT_CSS, dashboardCss, DASHBOARD_JAVA_PACKAGE_PATH);

        ScUtil.writeToFile(ScUtil.DASHBOARD_CLASSNAME +
                ScUtil.PRESENTER +
                ScUtil.DOT_JAVA,
                dashboardPresenter,
                DASHBOARD_JAVA_PACKAGE_PATH);

        ScUtil.writeToFile(ScUtil.DASHBOARD_CLASSNAME +
                ScUtil.VIEW +
                ScUtil.DOT_JAVA,
                dashboardView,
                DASHBOARD_JAVA_PACKAGE_PATH);

        ScUtil.writeToFile(ScUtil.DASHBOARD_CLASSNAME +
                ScUtil.DOT_FXML,
                dashboardFxml,
                DASHBOARD_JAVA_PACKAGE_PATH);


        /*Generating generic DAO*/
        daoContex = new VelocityContext();
        daoContex.put("mainJavaPackage", w.getMainJavaPackage());
        daoContex.put("rootJavaPackage", w.getRootJavaPackage());
        daoContex.put("appPackage", ScUtil.APP);
        daoContex.put("className", ScUtil.DASHBOARD_CLASSNAME);
        daoContex.put("classNameInstance", ScUtil.DASHBOARD_CLASSNAME_INSTANCE);
        daoContex.put("objectName", w.getObjectName());
        daoContex.put("javaPackage", w.getRootJavaPackage() +
                ScUtil.DOT +
                ScUtil.DAO_PACKAGE);
        daoContex.put("daoGeneric", ScUtil.DAO_GENERIC);
        daoContex.put("daoService", ScUtil.DAO_SERVICE);

        StringWriter genericDaoImp = new StringWriter();
        genericDaoImpTemplate.merge(daoContex, genericDaoImp);

        StringWriter genericServiceImpl = new StringWriter();
        genericServiceImpTemplate.merge(daoContex, genericServiceImpl);


        StringWriter iGenericDao = new StringWriter();
        iGenericDaoTemplate.merge(daoContex, iGenericDao);


        StringWriter iGenericService = new StringWriter();
        iGenericServiceTemplate.merge(daoContex, iGenericService);


        ScUtil.writeToFile(ScUtil.GENERIC_DAO_IMPL +
                        ScUtil.DOT_JAVA,
                genericDaoImp,
                DAO_JAVA_PACKAGE_PATH +
                        File.separator +
                        ScUtil.DAO_GENERIC);

        ScUtil.writeToFile(ScUtil.IGENERIC_DAO +
                        ScUtil.DOT_JAVA,
                iGenericDao,
                DAO_JAVA_PACKAGE_PATH +
                        File.separator +
                        ScUtil.DAO_GENERIC);

        ScUtil.writeToFile(ScUtil.GENERIC_SERVICE_IMPL +
                        ScUtil.DOT_JAVA,
                genericServiceImpl,
                DAO_JAVA_PACKAGE_PATH +
                        File.separator +
                        ScUtil.DAO_SERVICE);

        ScUtil.writeToFile(ScUtil.IGENERIC_SERVICE +
                        ScUtil.DOT_JAVA,
                iGenericService,
                DAO_JAVA_PACKAGE_PATH +
                        File.separator +
                        ScUtil.DAO_SERVICE);




        /* Pager code generator*/
        pagerContex = new VelocityContext();
        pagerContex.put("rootJavaPackage", w.getRootJavaPackage());
        pagerContex.put("utilPackage", w.getUtilJavaPackage());
        pagerContex.put("className", w.getClassName());
        pagerContex.put("classNameInstance", w.getClassNameInstance());
        pagerContex.put("objectName", w.getObjectName());
        pagerContex.put("pagerPackage", w.getUtilJavaPackage() +
                ScUtil.DOT +
                ScUtil.PAGER_PACKAGE);
        pagerContex.put("daoGeneric", ScUtil.DAO_GENERIC);
        pagerContex.put("daoService", ScUtil.DAO_SERVICE);
        pagerContex.put("javaPackage", w.getRootJavaPackage() +
                ScUtil.DOT +
                ScUtil.DAO_PACKAGE);
        pagerContex.put("daoService", ScUtil.DAO_SERVICE);

        StringWriter ipager = new StringWriter();
        iPagerTemplate.merge(pagerContex, ipager);

        StringWriter pager = new StringWriter();
        pagerTemplate.merge(pagerContex, pager);


        ScUtil.writeToFile(ScUtil.IPAGER +
                        ScUtil.DOT_JAVA,
                ipager,
                UTIL_JAVA_PACKAGE_PATH +
                        File.separator +
                        ScUtil.PAGER_PACKAGE);

        ScUtil.writeToFile(ScUtil.PAGER +
                        ScUtil.DOT_JAVA,
                pager,
                UTIL_JAVA_PACKAGE_PATH +
                        File.separator +
                        ScUtil.PAGER_PACKAGE);




    }


}
