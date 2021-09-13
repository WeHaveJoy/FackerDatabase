package faker;

import com.github.javafaker.Faker;

import java.sql.*;

public class FakerDatabase {

    public static void main(String[] args)
    {
        Connection connection = null;
        try
        {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:Faker.db");
            Statement statement = connection.createStatement();
            String instertingData = "INSERT INTO PERSON VALUES (?,?,?,?,?,?,?)";


            statement.executeUpdate("drop table if exists person");
            statement.executeUpdate("create table person (id integer primary key, firstName string , lastName string , cityName string, state string, country string, cellphone integer )");
//            statement.executeUpdate("insert into person values(1, 'leo', 'Booi', ' Bustermouth', 'Arizona', 'Trinidad', '1-945-212-6576')");
            ResultSet rSet = statement.executeQuery("select * from person");
            PreparedStatement  prepared = connection.prepareStatement(instertingData);
//
            for (int i =0; i < 1000; i++)
            {

                Faker faker = new Faker();
                 String firstName= faker.name().firstName();
                 String lastName=  faker.name().lastName();
                 String cityName= faker.address().cityName();
                 String state= faker.address().state();
                 String country= faker.address().country();
                 String cellPhone= faker.phoneNumber().cellPhone();

//                System.out.println("First name is: "+faker.name().firstName());
//                System.out.println("Last name is: "+faker.name().lastName());
//                System.out.println("City name is: "+faker.address().cityName());
//                System.out.println("State name is: "+faker.address().state());
//                System.out.println("Country name is: "+faker.address().country());
//                System.out.println("Cell number is: "+faker.phoneNumber().cellPhone());



                prepared.setString(2,firstName);
                prepared.setString(3,lastName);
                prepared.setString(4,cityName);
                prepared.setString(5,state);
                prepared.setString(6,country);
                prepared.setString(7,cellPhone);

                prepared.executeUpdate();

            }
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }

    }
}
