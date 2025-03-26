package gdg.daejuju.daehakjumak.sms.service;


import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsService {
    final DefaultMessageService messageService;

    public SmsService(@Value("${API_KEY}") String apiKey,
                      @Value("${API_SECRET_KEY}") String apiSecretKey) {
        // coolsms 시크릿키
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecretKey, "https://api.coolsms.co.kr");
    }


    // 문자 전송 기능
    public SingleMessageSentResponse sendSMS(String sendTo, String text) {
        Message message = new Message();

        message.setFrom("01074133150");
        message.setTo(sendTo);
        message.setText(text);

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);

        return response;
    }
}
