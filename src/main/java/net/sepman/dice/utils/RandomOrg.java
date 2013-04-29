package net.sepman.dice.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sepman.dice.service.ThrowServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class RandomOrg {
	static Logger log = LoggerFactory.getLogger(RandomOrg.class.getName());
	private static List numbers = new ArrayList();
	
	public static int getRandom(int sides){
		//http://www.random.org/integers/?num=10&min=1&max=6&col=1&base=10&format=plain&rnd=new
		String url = "http://www.random.org/integers/?num=1&min=1&max="+(sides)+"&col=1&base=10&format=plain&rnd=new";
		RestTemplate restTemplate = new RestTemplate();
		
		String result = restTemplate.getForObject(url, String.class, "");
		log.info("Generated random:" + result);
		
		int i;
		try {
			i = Integer.parseInt(result.trim());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Random.Org says:" + result);
		}
		return i;
	}

	public static List<String> getRandom(int sides, int amount){
		//http://www.random.org/integers/?num=10&min=1&max=6&col=1&base=10&format=plain&rnd=new
		String url = "http://www.random.org/integers/?num="+amount+"&min=1&max="+(sides)+"&col=1&base=10&format=plain&rnd=new";
		RestTemplate restTemplate = new RestTemplate();
		
		String result = restTemplate.getForObject(url, String.class, "");
		log.info("Generated random:" + result);
		
		String lines[] = result.split("\\r?\\n");
		
		return new ArrayList<String>(Arrays.asList(lines));
	}
	
	public static void main(String[] args) {
		RandomOrg.getRandom(6, 5);
	}
}
