package OOD.BuilderPattern;

/**
 * Created by tianbingleng on 22/10/2017.
 */

/**
 * The builder pattern is an object creation software design pattern.
 * Unlike the abstract factory pattern and the factory method pattern whose intention is to enable polymorphism,
 * the intention of the builder pattern is to find a solution to the telescoping constructor anti-pattern that
 * occurs when the increase of object constructor parameter combination leads to an exponential list of constructors.
 * 重点来了！！！！
 * Instead of using numerous constructors, the builder pattern uses another object, a builder,
 * that receives each initialization parameter step by step and then returns the resulting constructed object at once.
 *
 * The builder pattern has another benefit:
 * It can be used for objects that contain flat data (HTML code, SQL query, X.509 certificate…),
 * that is to say, data that can't be easily edited step by step and hence must be edited at once.
 *
 * Builder often builds a Composite. Often, designs start out using Factory Method (less complicated, more customizable, subclasses proliferate)
 * and evolve toward Abstract Factory, Prototype, or Builder (more flexible, more complex) as the designer discovers where more flexibility is needed.
 * Sometimes creational patterns are complementary:
 * Builder can use one of the other patterns to implement which components are built.
 * Builders are good candidates for a fluent interface.
 *
 * */
public class User {
    private final String firstName; // required, and immutable
    private final String lastName; // required
    private int age;
    private String phone;
    private String address;

    private User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phone = builder.phone;
        this.address = builder.address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public static class UserBuilder {
        private final String firstName; // required, and immutable
        private final String lastName; // required
        private int age = 0; // default value is 0
        private String phone = ""; // default value is an emoty str
        private String address; // default value is null

        public UserBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        // all of the following methods are used to set values for optional fields
        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public static void main(String[] args) {
        User user = new User.UserBuilder("San", "Zhang")
                            .age(25)
                            .phone("12345678")
                            .address("Fake address")
                            .build();
        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        System.out.println(user.getAge());
        System.out.println(user.getPhone());
        System.out.println(user.getAddress());
    }
}
