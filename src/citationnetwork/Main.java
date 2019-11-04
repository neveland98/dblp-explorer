package citationnetwork;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class Main {

	public static void main(String[] args) {
		
		String fileName = args[0];
		String keyword = args[1];
		int maxtier = Integer.valueOf(args[2]);
		List<JSONObject> list = new ArrayList<JSONObject>();
		JSONParser p = new JSONParser();
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			//3. convert it into a List
           list = stream .map(obj-> {
                try {
                	return (JSONObject)p.parse(obj);
                } 
                catch (ParseException e) {
                	e.printStackTrace();
                }
                return null;
            }
           )
            .collect(Collectors.toList());

		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		list.stream().filter(f -> f.get("title").toString().toLowerCase().contains(keyword.toLowerCase())).collect(Collectors.toList());
		list.forEach(obj -> System.out.println(obj.get("title").toString()));
		//list.forEach(System.out::println);
		//System.out.println(fileName);
		//System.out.println(keyword);
		//System.out.println(maxtier);
	}

}
