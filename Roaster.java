import java.util.Scanner;

public class Roaster {

	public static String section[];
	public static String choices[]; 
	
	//questions based on the SECTION 
	public static String smart[] = new String[] { //4
			"When do you start studying for a quiz?",
			"How do you take notes?",
			"How much coffee do you drink a day?",
			"Do you like Mondays?"
			};
	public static String attractive[] = new String[] { //4
			"Do you talk with your mouth full?", 
			"Do you take the stairs of the elevator?", 
			"How often do you walk along the shore in deep thought?", 
			"Is winter coming? (John Snow)"
			};
	public static String nice[] = new String[] { //4
			"How often do you judge people?", 
			"Do you regift?",
			"Do you bake?", 
			"How often do you help people"
			};
	public static String funny[] = new String[] { //4
			"Do you watch Spongebob?", 
			"How often do you laugh at your own jokes?", 
			"How often are you alone?",
			"Do you like Sourpatch?"
			};
	
	//The answer CHOICES based on CATEGORY
	public static String smartChoices[] = new String [] { 
			"[1] Ten minutes before" + "         " + 
			"[2] A day before" + "           " +
			"[3] A week in advance", 
			
			"[1] I don't need them" + "          " +
			"[2] On my computer"+ "   " +
			"[3] The good old fashioned way: pen and paper", 
			
			"[1] What is coffee? I prefer tea" + "  " +
			"[2] 1 cup" + "  " +
			"[3] 1 cup every hour",
			
			"[1] No" + "  " +
			"[2] Yes"
			};
	
	public static String attractiveChoices[] = new String[] { 
			"[1] Yes" + "          " +
			"[2] No", 
			
			"[1] Stairs" + "          " +
			"[2] Elevator", 
			
			"[1] Never" + "          " +
			"[2] Sometimes" + "          " +
			"[3] Always",
			
			"[1] It comes all the time" + "          " +
			"[2] Nighwalkers (no)"
			};
	
	public static String niceChoices[] = new String[] { 
			"[1] Never" + "          " +
			"[2] Sometimes" + "          " +
			"[3] Always",
			
			"[1] Yes" + "          " +
			"[2] No",
			
			"[1] Never" + "          " +
			"[2] Sometimes" + "          " +
			"[3] Always", 
			
			"[1] Never" + "          " +
			"[2] Sometimes" + "          " +
			"[3] Always", 
			}; 
	
	public static String funnyChoices[] = new String[]{ 
			"[1] Yes "+ "          " + 
			"[2] No", 
			
			"[1] Always " + "          " + 
			"[2] Sometimes" + "          " + 
			"[3] Never", 
			
			"[1] Never" + "          " +  
			"[2] Twice a week" + "          " + 
			"[3] Once a day", 
			
			"[1] Yes" + "          " +  
			"[2] No"
			};	
	
	
	//Actual ROASTS that will be printed as end result based on CATEGORY
	public static String smartRoasts[] = new String [] { 
			"If I wanted to kill myself I'd climb your ego and jump to your IQ.",  //1
			"Brains aren't everything. In your case they're nothing.", //2
			"Stupidity is not a crime so you are free to go.",//3
			"I could eat a bowl of alphabet soup and crap out a smarter comeback than what you just said.", //4
			"I'm glad to see you're not letting your education get in the way of your ignorance.", //5
			"I don't think you're stupid. You just have bad luck when thinking.",//6
			"If what you don't know can't hurt you, you're invulnerable.", //7
			"If ignorance is bliss, you must be the happiest person on earth.", //8
			"So, a thought crossed your mind? Must have been a long and lonely journey.", //9 
			"If I had a dollar for every time you said something smart, I'd be broke." //10 
			}; 
	
	public static String attractiveRoasts[] = new String [] { 
			"Maybe if you ate some of that make up you could be pretty on the inside.", //1
			"You’re like school in the summertime – no class.", //2
			"I'm trying to see thinkgs from your point of view, but I can't get my head that far up my ass.", //3
			"You shouldn't play hide and seek, no one would look for you.", //4
			"You should have been born in the Dark Ages; you look terrible in the light!",//5
			"No I'm not insulting you, I'm describing you.",//6
			"I don't want you to turn the other cheek; it's just as ugly.", //7
			"I could make a monkey out of you,"//8
			}; 
	
	public static String niceRoasts[] = new String [] { 
			"I could say nice things about you, but I'd rather tell the truth.",	//1
			"Do you have to leave so soon? I was just about to poison the tea.",//2
			"You bring everyone a lot of joy, when you leave the room.",			//3
			"You're so fake, Barbie is jealous.", 							   //4                      
			"I'm busy now. Can I ignore you some other time?",				//5
			"You're like Monday mornings, nobody likes you.", 			//6
			"We can always tell when you are lying. Your lips move.", 	//7
			"Anybody who told you to be yourself, simply couldn't have given you worse advice." //8
			}; 
	
	public static String funnyRoasts[] = new String [] { 
			" I'm jealous of all the people that haven't met you!", 	//1
			"You remind me of the ocean – you make me sick",		//2
			"You must be the arithmetic man – you add trouble, subtract pleasure, divide attention, and multiply ignorance.",	//3
			"You are living proof that God has a sense of humor.",		//4
			"I’ve had many cases of love that were just infatuation, but this hate I feel for you is the real thing.",		//4
			"I wish your charm could be bottled – then a cork could be put in it.",	//5
			"If laughter is the best medicine, your face must be curing the world.", 	//6
			"You're as useless as the 'ueue' in 'queue'. ", 			//7 
			"I'd agree with you but then we'd both be wrong.",		//8 
			"Don't feel bad. A lot of people have no talent!"	,	//9 
			"I like you. People say I've no taste, but I like you. "	//10
			};

			

	Scanner inputScanner = new Scanner(System.in);
	
	public static int valueInput; 

	public static int yourself;
	
	
	/**
	 * 
	 * @param yourself
	 * @return 
	 */
	 
	public static String[] category(int input) {
		if (input == 1) {
			section = smart;
			choices = smartChoices; 
			
		}
		if (input == 2) {
			section = attractive;
			choices = attractiveChoices; 
		}
		if (input == 3) {
			section = nice;
			choices = niceChoices; 
		}
		if (input == 4) {
			section = funny;
			choices = funnyChoices;
		}
		
		return (section); 
		
	}
	
	
	public static int score; 
	
	public static int total = 0; 
	public static int scoreCounter(int number) { 
		total = total + score; 
		return total; 
	}
	
	
	
	public static String answers; 
	/**
	 * 
	 * @param count the SCORE
	 * @param input  is the value that is entered for each response
	 * @return
	 */
	public static String answer(int count, int input) {
		if (input == 1) { 
			 answers = (smartRoasts[count - 1]);
		}
		if (input == 2) { 
			answers = (attractiveRoasts[count - 1]); 
		}
		if (input == 3) { 
			answers =  (niceRoasts[count - 1]); 
		}
		if (input == 4) { 
			answers = (funnyRoasts[count - 1]); 
		}
		
		return answers;
	}
	
/**
 * 
 * @param args
 */
	public static void main(String[] args) { 
		
		//prints out the first question which decides the category
		System.out.println("Type the number that matches your answers");
		Scanner inputScanner = new Scanner (System.in); 
		System.out.println("Would you consider yourself?"); 
		System.out.println(
				"[1] Smart" + "  " +
				"[2] Attractive" + "  " + 
				"[3] Nice" + "  " + 
				"[4] Funny" + "  "); 
		
		yourself = inputScanner.nextInt(); 
		System.out.println("");
		//category(yourself);
				
		
		//prints out the questions 
		System.out.println(category(yourself)[0]);
		System.out.println(choices[0]);
		System.out.println();
		score = inputScanner.nextInt(); 
		scoreCounter(score);
		
		System.out.println(category(yourself)[1]);
		System.out.println(choices[1]);
		System.out.println();
		score = inputScanner.nextInt(); 
		scoreCounter(score);
		
		System.out.println(category(yourself)[2]);
		System.out.println(choices[2]);
		System.out.println();
		score = inputScanner.nextInt(); 
		scoreCounter(score);
		
		System.out.println(category(yourself)[3]);
		System.out.println(choices[3]);
		System.out.println();
		score = inputScanner.nextInt(); 
		scoreCounter(score);
		
		
		//this is the last step: the roast will be printed @ end 
		System.out.println("score: " + total);
		System.out.println(answer(total, yourself));
		
		//closes the scanner 
		inputScanner.close();	
	}
}
