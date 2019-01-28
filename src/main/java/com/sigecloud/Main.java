package com.sigecloud;

import com.sigecloud.pojo.Widget;
import com.sigecloud.util.CodeGenerator;
import com.sigecloud.util.ScUtil;

public class Main {
    public static void main( String[] args )
            throws Exception
    {

        Widget widget = ScUtil.loadJsonFile("test.json");

        CodeGenerator codeGenerator = new CodeGenerator(widget);

        codeGenerator.generate();

        System.out.println(widget);

        /*

        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, "src/main/resources/templates");
        ve.init();

        Template t = ve.getTemplate("controller.vm");
        VelocityContext context = new VelocityContext();
        context.put("className", "Impuesto");
        context.put("classNameInstance", "impuesto");
        context.put("daoName", "impuestoDao");
        context.put("objectName", "io");
        context.put("sortBy", "nombre");
        context.put("findBy", "nombre");

        List<Campo> campos = new ArrayList<Campo>();

        campos.add(new Campo("id", "int", null,0, 0, 0, "Id", 0));
        campos.add(new Campo("nombre", "String", "Debe ingresar en nombre del Impuesto",2, 20, 1, "Nombre del Impuesto", 1));
        campos.add(new Campo("porcentaje", "BigDecimal", "Debe ingresar en porcentaje del Impuesto",2, 10, 1, "Porcentaje del Impuesto", 1));
        campos.add(new Campo("tipo", "String", "Seleccine el tipo de Impuesto",2, 10, 1, "Tipo de impuesto", 1));
        campos.add(new Campo("codigoSri", "String", "Seleccine el tipo de Impuesto",2, 10, 1, "Codigo SRI", 1));
        context.put("fields", campos);


        StringWriter writer = new StringWriter();
        t.merge( context, writer );
        System.out.println( writer.toString() );

        */
    }
}
