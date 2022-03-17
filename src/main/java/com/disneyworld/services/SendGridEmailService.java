package com.disneyworld.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class SendGridEmailService implements EmailService{
	
	@Value("${app.sendgrid.key}")
	private String appKey;
	
	//Mail building:
	@Override
	public String sendText(String email) {
		String from = "pozzolidev@gmail.com";
		String to = email;
		String subject = "Welcome to the Alkemy Disney Challenge";
		Content content = new Content("text/plain", "Hola, mi nombre es Martin Pozzoli. Espero que tengas una buena "
				+ "experiencia en el uso de esta API y que mi desarrollo cumpla con todos los requisitos. Saludos!");
		
		Response response = sendEmail(from, to, subject, content);
		
		System.out.println("Status Code: " + response.getStatusCode() + ", Body: " + response.getBody() + ", Headers: "
                + response.getHeaders());
		
		return "Check your inbox.";
	}
	
	//Mail sending:
	private Response sendEmail(String from, String to, String subject, Content content) {
        Mail mail = new Mail(new Email(from), subject, new Email(to), content);
        Request request = new Request();
        Response response = null;
        SendGrid sg = new SendGrid(appKey);
        try {
        	sg.setHost("api.sendgrid.com");
        	sg.setVersion("v3");
        	
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            
            response = sg.api(request);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return response;
    }



	
}
