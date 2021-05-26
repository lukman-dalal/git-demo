package com.lukman.downloader.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;

/**
 * Servlet implementation class VideoDownloader
 */
@WebServlet("/vd")
public class VideoDownloader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
    		final String ytResponse= DownloadText("https://www.youtube.com/watch?v=q5_GX1QgJjM");
    		PrintWriter pw= response.getWriter();
    		pw.write(ytResponse);
    		
    	}

    	public String DownloadText(String url) throws IOException{
    	      String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:8.0.1)";
    	      HttpClient client = new DefaultHttpClient();
    	      client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, userAgent);

    	      HttpGet request = new HttpGet(url);
    	      HttpResponse response = client.execute(request);

    	      String html = "";
    	      InputStream in = response.getEntity().getContent();
    	      BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    	      StringBuilder str = new StringBuilder();
    	      String line = null;
    	      while((line = reader.readLine()) != null)
    	      {
    	          str.append(line);
    	      }
    	      in.close();
    	      html = str.toString();

    	      return html;
    	    }
}
