import java.util.function.Consumer;

public class FluentMailer {

    private String from;
    private FluentMailer() {
    }

    public FluentMailer from(final String address) { /*... */
        this.from = address;
        return this;
    }

    @Override
    public String toString() {
        return "FluentMailer{" +
                "from='" + from + '\'' +
                '}';
    }

    public FluentMailer to(final String address) { /*... */
        ;
        return this;
    }

    public FluentMailer subject(final String line) { /*... */
        ;
        return this;
    }

    public FluentMailer body(final String message) { /*... */
        ;
        return this;
    }

    public static void send(final Consumer<FluentMailer> block) {
        final FluentMailer mailer = new FluentMailer();//i've passed this mailer to block
        block.accept(mailer);
        System.out.println("sending..." + mailer);
    }
//...


    public static void main(String[] args) {
        FluentMailer.send(mailer ->
                mailer.from("build@agiledeveloper.com")
                .to("venkats@agiledeveloper.com")
                .subject("build notification")
                .body("...much better..."));
    }
}