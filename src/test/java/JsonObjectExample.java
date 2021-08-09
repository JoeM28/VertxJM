
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.vertx.core.json.JsonArray;
import org.junit.jupiter.api.Test;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class JsonObjectExample {

  @Test
  void  jsonobjectcanbemapped() {
    final JsonObject myJsonObject = new JsonObject();
    myJsonObject.put("id", 1);
    myJsonObject.put("name", "Joe");
    myJsonObject.put("USACountry",true);

    final String encoded1 = myJsonObject.encode();

    assertEquals("{\"id\":1,\"name\":\"Joe\",\"USACountry\":true}", encoded1 );

    final JsonObject decodedJsonObject1 = new JsonObject(encoded1);

    assertEquals(myJsonObject, decodedJsonObject1);

  }

   @Test
  void jsonObjectfromMap() {
    final Map<String, Object> myMap = new HashMap<>();
    myMap.put("id" , 1);
    myMap.put("City", "Weston");
    myMap.put("Country" , "USA");
    final JsonObject asJsonObject = new JsonObject(myMap);
    assertEquals(myMap, asJsonObject.getMap());
    assertEquals(1,asJsonObject.getInteger("id"));
    assertEquals("Miami",asJsonObject.getString("City"));

   }

   @Test
  void JsonArrayMapped(){
    final JsonArray myJSONArray = new JsonArray();

    myJSONArray
      .add(new JsonObject().put("id",1))
      .add(new JsonObject().put("id",2))
      .add(new JsonObject().put("id",3))
      .add(new JsonObject().put("id",4))
      ;
    assertEquals("[{\"id\":1},{\"id\":2},{\"id\":3},{\"id\":4}]",myJSONArray.encode());


   }


   @Test


}
