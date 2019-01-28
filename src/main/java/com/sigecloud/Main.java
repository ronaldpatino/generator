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

    }
}
