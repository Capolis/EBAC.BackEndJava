public class Main {
    public static void main(String[] args) {
        
        // Testando com notificação por E-mail
        Notificador email = new EmailNotificador();
        UsuarioService servicoEmail = new UsuarioService(email);
        servicoEmail.registrar("Caio");
        
        System.out.println("-----------------------------------");
        
        // Testando com notificação por SMS
        Notificador sms = new SmsNotificador();
        UsuarioService servicoSms = new UsuarioService(sms);
        servicoSms.registrar("Ana");
        
        System.out.println("-----------------------------------");
        
        // Testando com notificação por WhatsApp
        Notificador whatsapp = new WhatsappNotificador();
        UsuarioService servicoWhatsapp = new UsuarioService(whatsapp);
        servicoWhatsapp.registrar("Marcos");
    }
}