package com.dangdang.member.service;

import com.dangdang.member.dto.BusinessRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.core.env.Environment;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
@Transactional
@RequiredArgsConstructor
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:application-local.properties"),
        @PropertySource("classpath:application-personal.properties")
})
public class BusinessService {

    private final Environment env;

    public JSONObject makeJSON(BusinessRequest input){ // 입력 받은 사업자 정보

        JSONObject result = new JSONObject();

        JSONArray arr = new JSONArray();
        JSONObject company = new JSONObject(); //사업자 정보

        company.put("b_no",input.getCompanyNo());
        company.put("start_dt",input.getOpenDay());
        company.put("p_nm",input.getManagerName());
        company.put("p_nm2","");
        company.put("b_nm","");
        company.put("corp_no","");
        company.put("b_sector","");
        company.put("b_type","");

        arr.add(company);
        result.put("businesses",arr);

        return result;
    }

    public void getInfo(BusinessRequest input) throws IOException {
        JSONObject object = this.makeJSON(input);

        String apiUrl = "https://api.odcloud.kr/api/nts-businessman/v1/validate?serviceKey=" + env.getProperty("bns_service_apikey");
        URL url = null;
        String readLine = null;
        StringBuilder buffer = null;
        OutputStream outputStream = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        HttpURLConnection urlConnection = null;

        int connTimeout = 5000;
        int readTimeout = 3000;

        try
        {
            url = new URL(apiUrl);

            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setConnectTimeout(connTimeout);
            urlConnection.setReadTimeout(readTimeout);
            urlConnection.setRequestProperty("Content-Type", "application/json;");
            urlConnection.setDoOutput(true);
            urlConnection.setInstanceFollowRedirects(true);

            outputStream = urlConnection.getOutputStream();

            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            bufferedWriter.write(object.toJSONString());
            bufferedWriter.flush();

            buffer = new StringBuilder();
            if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
                while((readLine = bufferedReader.readLine()) != null)
                {
                    buffer.append(readLine).append("\n");
                }
            }
            else
            {
                buffer.append("\"code\" : \""+urlConnection.getResponseCode()+"\"");
                buffer.append(", \"message\" : \""+urlConnection.getResponseMessage()+"\"");
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if (bufferedWriter != null) { bufferedWriter.close(); }
                if (outputStream != null) { outputStream.close(); }
                if (bufferedReader != null) { bufferedReader.close(); }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }


        System.out.println("결과 : " + buffer.toString());
    }

}
