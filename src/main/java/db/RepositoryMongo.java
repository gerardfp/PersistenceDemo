package db;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.UuidRepresentation;
import org.bson.codecs.UuidCodec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public abstract class RepositoryMongo<T> implements Repository<T> {
    static String uri = "mongodb://localhost";
    static MongoDatabase database;



    public void init() {
        database = MongoClients.create(uri).getDatabase("sampledb")
                .withCodecRegistry(fromRegistries(
                        MongoClientSettings.getDefaultCodecRegistry(),
                        fromProviders(PojoCodecProvider.builder().automatic(true).build())));
    }

    @Override
    public void insert(T t) {
        MongoCollection<T> collection =  (MongoCollection<T>) database.getCollection(getTableName(), getType());
        collection.insertOne(t);
    }

    @Override
    public List<T> getAll() {
        MongoCollection<T> collection = (MongoCollection<T>) database.getCollection(getTableName(), getType());
        List<T> list = new ArrayList<>();
        return collection.find().into(list);
    }
}
