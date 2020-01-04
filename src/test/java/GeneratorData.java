import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class GeneratorData {
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
     static class PositiveData {
         static Faker faker = new Faker(new Locale("ru"));
         static String date = simpleDateFormat.format(faker.date().future(1500, 2, TimeUnit.DAYS));
         static String dateForReplan = simpleDateFormat.format(faker.date().future(1500, 2, TimeUnit.DAYS));
         static String name = faker.name().firstName() + " " + faker.name().lastName();
         static String phone = faker.numerify("+7 ### ### ## ##");
    }
    static class NegativeData {
         static Faker faker = new Faker(new Locale("eng"));
         static String city = faker.address().cityName();
         static String date = simpleDateFormat.format(faker.date().past(300, -2, TimeUnit.DAYS));
         static String name = faker.name().firstName() + " " + faker.name().lastName();
    }
}
