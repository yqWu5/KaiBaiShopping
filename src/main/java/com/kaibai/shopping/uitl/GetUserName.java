package com.kaibai.shopping.uitl;

//通过token提取username
public class GetUserName {

    public static String getUserName(String token) {
        int index = token.indexOf("-") + 1;
        String userName = token.substring(index, token.length() - 1);
        return userName;
    }

    public static String getUserRole(String token) {
        String roleCode = token.substring(token.length() - 1);
        return roleCode;
    }

}
