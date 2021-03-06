package com.sigecloud.pojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Widget {

    private String className;
    private String classNameInstance;
    private String daoName;
    private String objectName;
    private String sortByField;
    private String findByField;
    private String rootJavaPackage;
    private String javaPackage;
    private String mainJavaPackage;
    private String validatorJavaPackage;
    private String utilJavaPackage;
    private List<Field> fields = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassNameInstance() {
        return classNameInstance;
    }

    public void setClassNameInstance(String classNameInstance) {
        this.classNameInstance = classNameInstance;
    }

    public String getDaoName() {
        return daoName;
    }

    public void setDaoName(String daoName) {
        this.daoName = daoName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getSortByField() {
        return sortByField;
    }

    public void setSortByField(String sortByField) {
        this.sortByField = sortByField;
    }

    public String getFindByField() {
        return findByField;
    }

    public void setFindByField(String findByField) {
        this.findByField = findByField;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public String getJavaPackage() {
        return javaPackage;
    }

    public void setJavaPackage(String javaPackage) {
        this.javaPackage = javaPackage;
    }

    public String getValidatorJavaPackage() {
        return validatorJavaPackage;
    }

    public String getMainJavaPackage() {
        return mainJavaPackage;
    }

    public void setMainJavaPackage(String mainJavaPackage) {
        this.mainJavaPackage = mainJavaPackage;
    }

    public String getRootJavaPackage() {
        return rootJavaPackage;
    }

    public void setRootJavaPackage(String rootJavaPackage) {
        this.rootJavaPackage = rootJavaPackage;
    }

    public void setValidatorJavaPackage(String validatorJavaPackage) {
        this.validatorJavaPackage = validatorJavaPackage;
    }

    public String getUtilJavaPackage() {
        return utilJavaPackage;
    }

    public void setUtilJavaPackage(String utilJavaPackage) {
        this.utilJavaPackage = utilJavaPackage;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}