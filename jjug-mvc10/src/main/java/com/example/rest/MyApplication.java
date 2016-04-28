package com.example.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * JAX-RSを有効化するための設定クラスです。
 * @author tada
 */
@ApplicationPath("api")
public class MyApplication extends Application {
}
