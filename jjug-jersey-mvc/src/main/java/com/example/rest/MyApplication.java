package com.example.rest;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.MvcFeature;

/**
 * JAX-RSを有効化するための設定クラスです。
 * クラスの登録が必要になるため、Jersey独自のApplicationサブクラスである
 * ResourceConfigを継承しています。
 * @author tada
 */
@ApplicationPath("api")
public class MyApplication extends ResourceConfig {
    public MyApplication() {
        // Jersey MVCを有効化する
        register(MvcFeature.class);
//        register(ThymeleafTemplateProcessor.class);
        // ビューの保存フォルダを指定する
        property(MvcFeature.TEMPLATE_BASE_PATH, "/WEB-INF/views/");
        // このクラスが入っているパッケージ以下の全パッケージ内のクラスを登録対象にする
        packages(true, this.getClass().getPackage().getName());
    }
}
