class EmailNotificador implements Notificador {
    @Override
    public void enviarMensagem(String mensagem) {
        System.out.println("[E-MAIL] " + mensagem);
    }
}