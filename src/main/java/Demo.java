import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

public class Demo {
    public static void main(String[] args) {
        MongoClient cliente = new MongoClient();
        MongoDatabase db = cliente.getDatabase("nba");
        MongoCollection<Document> coll = db.getCollection("jugadores");
        for (Document d : coll.find(eq("country", "Spain"))){
            System.out.printf("%s %s\n", d.getString("firstName"), d.getString("lastName"));
        }
//        coll.find().forEach((Document d) -> (System.out.println(d.toJson()));
//        Document d = new Document("lastName", "Aldama").append("firstName", "Santi");
//        coll.insertOne(d);

//        coll.deleteOne(and(eq("firstName", "Alex"), eq("lastName", "Abrines")));
        coll.updateOne(eq("lastName", "Calderon"), Updates.set("firstName", "José Manuel"));

        FindIterable<Document> iterobj = coll.find(eq("country", "Spain")).projection(exclude("country"));

        Iterator itr = iterobj.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }

//        MongoCursor<Document> lista = coll.find(eq("country", "Spain")).projection(exclude("country")).iterator();
//        for (Document d1 : lista) {
//            System.out.println(d1.toJson());
//        }

        List<Document> lista = coll.find().into(new ArrayList<Document>());
        System.out.println("\n" + lista.get(0));
    }
}
