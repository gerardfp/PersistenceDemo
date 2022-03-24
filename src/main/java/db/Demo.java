package db;

public class Demo {

    public static void main(String[] args) {
//        boolean mongo = true;
        boolean mongo = false;

        PersonRepository personRepository;
        ThingRepository thingRepository;
        if(mongo) {
            personRepository = new PersonRepositoryMongo();
            thingRepository = new ThingRepositoryMongo();
        } else {
            personRepository = new PersonRepositoryMySQL();
            thingRepository = new ThingRepositoryMySQL();
        }

        personRepository.init();
        thingRepository.init();

        personRepository.insert(new Person("Pepe"));
        personRepository.getAll().forEach(System.out::println);


        thingRepository.insert(new Thing("Yacht"));
        thingRepository.getAll().forEach(System.out::println);
    }
}
