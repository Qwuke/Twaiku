package net.twaiku.dto;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.ui.Model;



//testing with implementing TwaikuDAO
public class TwaikuDAOImpl implements TwaikuDAO {
	
	public String updateTwaikuDatabaseTweets(Model model){
		
		Configuration config = new Configuration();
		

		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		TwaikuDTO newTwaikuDTO = new TwaikuDTO();

		newTwaikuDTO.setTweetID(0);
		newTwaikuDTO.setTweetString("tweetString");
		newTwaikuDTO.setUserName("userName");

		session.save(newTwaikuDTO);
		tx.commit();
		session.close();
		model.addAttribute("updateTwaiku", newTwaikuDTO);
		
	
		
		return "";
	
	}

	@Override
	public void updateTwaikuDatabase() {
		
	}
	@Override
	public void deleteFromTwaikuDatabase() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTweetHaiku() {
		// TODO Auto-generated method stub
		return null;
	}


}
