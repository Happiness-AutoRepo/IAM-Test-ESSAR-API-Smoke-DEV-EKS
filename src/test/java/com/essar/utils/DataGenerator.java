package com.essar.utils;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataGenerator {
	private static final Logger logger = LoggerFactory.getLogger(DataGenerator.class);
	final static String[] maleNames = new String[]{"Jacob","Michael","Matthew","Joshua","Christopher","Nicholas","Andrew","Joseph","Daniel","Tyler","William","Brandon","Ryan","John","Zachary","David","Anthony","James","Justin","Alexander","Jonathan","Christian","Austin","Dylan","Ethan","Benjamin","Noah","Samuel","Robert","Nathan","Cameron","Kevin","Thomas","Jose","Hunter","Jordan","Kyle","Caleb","Jason","Logan","Aaron","Eric","Brian","Gabriel","Adam","Jack","Isaiah","Juan","Luis","Connor","Charles","Elijah","Isaac","Steven","Evan","Jared","Sean","Timothy","Luke","Cody","Nathaniel","Alex","Seth","Mason","Richard","Carlos","Angel","Patrick","Devin","Bryan","Cole","Jackson","Ian","Garrett","Trevor","Jesus","Chase","Adrian","Mark","Blake","Sebastian","Antonio","Lucas","Jeremy","Gavin","Miguel","Julian","Dakota","Alejandro","Jesse","Dalton","Bryce","Tanner","Kenneth","Stephen","Jake","Victor","Spencer","Marcus","Paul","Brendan","Jeremiah","Xavier","Jeffrey","Tristan","Jalen","Jorge","Edward","Riley","Wyatt","Colton","Joel","Maxwell","Aidan","Travis","Shane","Colin","Dominic","Carson","Vincent","Derek","Oscar","Grant","Eduardo","Peter","Henry","Parker","Hayden","Collin","George","Bradley","Mitchell","Devon","Ricardo","Shawn","Taylor","Nicolas","Francisco","Gregory","Liam","Kaleb","Preston","Erik","Alexis","Owen","Omar","Diego","Dustin","Corey","Fernando","Clayton","Carter","Ivan","Jaden","Javier","Alec","Johnathan","Scott","Manuel","Cristian","Alan","Raymond","Brett","Max","Andres","Gage","Mario","Dawson","Dillon","Cesar","Wesley","Levi","Jakob","Chandler","Martin","Malik","Edgar","Trenton","Sergio","Josiah","Nolan","Marco","Peyton","Harrison","Hector","Micah","Roberto","Drew","Brady","Erick","Conner","Jonah","Casey","Jayden","Emmanuel","Edwin","Andre","Phillip","Brayden","Landon","Giovanni","Bailey","Ronald","Braden","Damian","Donovan","Ruben","Frank","Pedro","Gerardo","Andy","Chance","Abraham","Calvin","Trey","Cade","Donald","Derrick","Payton","Darius","Enrique","Keith","Raul","Jaylen","Troy","Jonathon","Cory","Marc","Skyler","Rafael","Trent","Griffin","Colby","Johnny","Eli","Chad","Armando","Kobe","Caden","Cooper","Marcos","Elias","Brenden","Israel","Avery","Zane","Dante","Josue","Zackary","Allen","Mathew","Dennis","Leonardo","Ashton","Philip","Julio","Miles","Damien","Ty","Gustavo","Drake","Jaime","Simon","Jerry","Curtis","Kameron","Lance","Brock","Bryson","Alberto","Dominick","Jimmy","Kaden","Douglas","Gary","Brennan","Zachery","Randy","Louis","Larry","Nickolas","Tony","Albert","Fabian","Keegan","Saul","Danny","Tucker","Damon","Myles","Arturo","Corbin","Deandre","Ricky","Kristopher","Lane","Pablo","Darren","Zion","Jarrett","Alfredo","Micheal","Angelo","Carl","Oliver","Kyler","Tommy","Walter","Dallas","Jace","Quinn","Theodore","Grayson","Lorenzo","Joe","Arthur","Bryant","Brent","Roman","Russell","Ramon","Lawrence","Moises","Aiden","Quentin","Tyrese","Jay","Tristen","Emanuel","Salvador","Terry","Morgan","Jeffery","Esteban","Tyson","Braxton","Branden","Brody","Craig","Marvin","Ismael","Rodney","Isiah","Maurice","Marshall","Ernesto","Emilio","Brendon","Kody","Eddie","Malachi","Abel","Keaton","Jon","Shaun","Skylar","Nikolas","Ezekiel","Santiago","Kendall","Axel","Camden","Trevon","Bobby","Conor","Jamal","Lukas","Malcolm","Zackery","Jayson","Javon","Reginald","Zachariah","Desmond","Roger","Felix","Dean","Johnathon","Quinton","Ali","Davis","Gerald","Demetrius","Rodrigo","Billy","Rene","Reece","Justice","Kelvin","Leo","Guillermo","Chris","Kevon","Steve","Frederick","Clay","Weston","Dorian","Hugo","Orlando","Roy","Terrance","Kai","Khalil","Graham","Noel","Nathanael","Willie","Terrell","Tyrone","Camron","Mauricio","Amir","Darian","Jarod","Nelson","Kade","Reese","Kristian","Garret","Marquis","Rodolfo","Dane","Felipe","Todd","Elian","Walker","Mateo","Jaylon","Kenny","Bruce","Ezra","Ross","Damion","Francis","Tate","Byron","Reid","Warren","Randall","Bennett","Jermaine","Triston","Jaquan","Harley","Jessie","Franklin","Duncan","Charlie","Reed","Blaine","Braeden","Holden","Ahmad","Issac","Kendrick","Melvin","Sawyer","Solomon","Moses","Jaylin","Sam","Cedric","Mohammad","Alvin","Beau","Jordon","Elliot","Lee","Darrell","Jarred","Mohamed","Davion","Wade","Tomas","Jaxon","Uriel","Deven","Maximilian","Rogelio","Gilberto","Ronnie","Julius","Allan","Brayan","Deshawn","Joey","Terrence","Noe","Alfonso","Ahmed","Tyree","Tyrell","Jerome","Devan","Neil","Ramiro","Pierce","Davon","Devonte","Jamie","Leon","Adan","Eugene","Stanley","Marlon","Quincy","Leonard","Wayne","Will","Alvaro","Ernest","Harry","Addison","Ray","Alonzo","Jadon","Jonas","Keyshawn","Rolando","Mohammed","Tristin","Donte","Dominique","Leonel","Wilson","Gilbert","Coby","Dangelo","Kieran","Colten","Keenan","Koby","Jarrod","Dale","Harold","Toby","Dwayne","Elliott","Osvaldo","Cyrus","Kolby","Sage","Coleman","Declan","Adolfo","Ariel","Brennen","Darryl","Trace","Orion","Shamar","Efrain","Keshawn","Rudy","Ulises","Darien","Braydon","Ben","Vicente","Nasir","Dayton","Joaquin","Karl","Dandre","Isaias","Rylan","Sterling","Cullen","Quintin","Stefan","Brice","Lewis","Gunnar","Humberto","Nigel","Alfred","Agustin","Asher","Daquan","Easton","Salvatore","Jaron","Nathanial","Ralph","Everett","Hudson","Marquise","Tobias","Glenn","Antoine","Jasper","Elvis"};
	final static String[] femaleNames = new String[]{"Emily","Hannah","Madison","Ashley","Sarah","Alexis","Samantha","Jessica","Elizabeth","Taylor","Lauren","Alyssa","Kayla","Abigail","Brianna","Olivia","Emma","Megan","Grace","Victoria","Rachel","Anna","Sydney","Destiny","Morgan","Jennifer","Jasmine","Haley","Julia","Kaitlyn","Nicole","Amanda","Katherine","Natalie","Hailey","Alexandra","Savannah","Chloe","Rebecca","Stephanie","Maria","Sophia","Mackenzie","Allison","Isabella","Amber","Mary","Danielle","Gabrielle","Jordan","Brooke","Michelle","Sierra","Katelyn","Andrea","Madeline","Sara","Kimberly","Courtney","Erin","Brittany","Vanessa","Jenna","Jacqueline","Caroline","Faith","Makayla","Bailey","Paige","Shelby","Melissa","Kaylee","Christina","Trinity","Mariah","Caitlin","Autumn","Marissa","Breanna","Angela","Catherine","Zoe","Briana","Jada","Laura","Claire","Alexa","Kelsey","Kathryn","Leslie","Alexandria","Sabrina","Mia","Isabel","Molly","Leah","Katie","Gabriella","Cheyenne","Cassandra","Tiffany","Erica","Lindsey","Kylie","Amy","Diana","Cassidy","Mikayla","Ariana","Margaret","Kelly","Miranda","Maya","Melanie","Audrey","Jade","Gabriela","Caitlyn","Angel","Jillian","Alicia","Jocelyn","Erika","Lily","Heather","Madelyn","Adriana","Arianna","Lillian","Kiara","Riley","Crystal","Mckenzie","Meghan","Skylar","Ana","Britney","Angelica","Kennedy","Chelsea","Daisy","Kristen","Veronica","Isabelle","Summer","Hope","Brittney","Lydia","Hayley","Evelyn","Bethany","Shannon","Michaela","Karen","Jamie","Daniela","Angelina","Kaitlin","Karina","Sophie","Sofia","Diamond","Payton","Cynthia","Alexia","Valerie","Monica","Peyton","Carly","Bianca","Hanna","Brenda","Rebekah","Alejandra","Mya","Avery","Brooklyn","Ashlyn","Lindsay","Ava","Desiree","Alondra","Camryn","Ariel","Naomi","Jordyn","Kendra","Mckenna","Holly","Julie","Kendall","Kara","Jasmin","Selena","Esmeralda","Amaya","Kylee","Maggie","Makenzie","Claudia","Kyra","Cameron","Karla","Kathleen","Abby","Delaney","Amelia","Casey","Serena","Savanna","Aaliyah","Giselle","Mallory","April","Raven","Adrianna","Christine","Kristina","Nina","Asia","Natalia","Valeria","Aubrey","Lauryn","Kate","Patricia","Jazmin","Rachael","Katelynn","Cierra","Alison","Macy","Nancy","Elena","Kyla","Katrina","Jazmine","Joanna","Tara","Gianna","Juliana","Fatima","Allyson","Gracie","Sadie","Guadalupe","Genesis","Yesenia","Julianna","Skyler","Tatiana","Alexus","Alana","Elise","Kirsten","Nadia","Sandra","Dominique","Ruby","Haylee","Jayla","Tori","Cindy","Sidney","Ella","Tessa","Carolina","Camille","Jaqueline","Whitney","Carmen","Vivian","Priscilla","Bridget","Celeste","Kiana","Makenna","Alissa","Madeleine","Miriam","Natasha","Ciara","Cecilia","Mercedes","Kassandra","Reagan","Aliyah","Josephine","Charlotte","Rylee","Shania","Kira","Meredith","Eva","Lisa","Dakota","Hallie","Anne","Rose","Liliana","Kristin","Deanna","Imani","Marisa","Kailey","Annie","Nia","Carolyn","Anastasia","Brenna","Dana","Shayla","Ashlee","Kassidy","Alaina","Rosa","Wendy","Logan","Tabitha","Paola","Callie","Addison","Lucy","Gillian","Clarissa","Destinee","Josie","Esther","Denise","Katlyn","Mariana","Bryanna","Emilee","Georgia","Deja","Kamryn","Ashleigh","Cristina","Baylee","Heaven","Ruth","Raquel","Monique","Teresa","Helen","Krystal","Tiana","Cassie","Kayleigh","Marina","Heidi","Ivy","Ashton","Clara","Meagan","Gina","Linda","Gloria","Jacquelyn","Ellie","Jenny","Renee","Daniella","Lizbeth","Anahi","Virginia","Gisselle","Kaitlynn","Julissa","Cheyanne","Lacey","Haleigh","Marie","Martha","Eleanor","Kierra","Tiara","Talia","Eliza","Kaylie","Mikaela","Harley","Jaden","Hailee","Madalyn","Kasey","Ashlynn","Brandi","Lesly","Elisabeth","Allie","Viviana","Cara","Marisol","India","Tatyana","Litzy","Melody","Jessie","Brandy","Alisha","Hunter","Noelle","Carla","Francesca","Tia","Layla","Krista","Zoey","Carley","Janet","Carissa","Iris","Kaleigh","Tyler","Susan","Tamara","Theresa","Yasmine","Tatum","Sharon","Alice","Yasmin","Tamia","Abbey","Alayna","Kali","Lilly","Bailee","Lesley","Mckayla","Ayanna","Serenity","Karissa","Precious","Jane","Maddison","Jayda","Kelsie","Lexi","Phoebe","Halle","Kiersten","Kiera","Tyra","Annika","Felicity","Taryn","Kaylin","Ellen","Kiley","Jaclyn","Rhiannon","Madisyn","Colleen","Joy","Pamela","Charity","Tania","Fiona","Alyson","Kaila","Annabelle","Emely","Angelique","Alina","Irene","Johanna","Regan","Janelle","Janae","Madyson","Paris","Justine","Chelsey","Sasha","Paulina","Mayra","Zaria","Skye","Cora","Brisa","Emilie","Felicia","Larissa","Macie","Tianna","Aurora","Sage","Lucia","Alma","Chasity","Ann","Deborah","Nichole","Jayden","Alanna","Malia","Carlie","Angie","Nora","Kailee","Sylvia","Carrie","Elaina","Sonia","Genevieve","Kenya","Piper","Marilyn","Amari","Macey","Marlene","Barbara","Tayler","Julianne","Brooklynn","Lorena","Perla","Elisa","Kaley","Leilani","Eden","Miracle","Devin","Aileen","Chyna","Athena","Esperanza","Regina","Adrienne","Shyanne","Luz","Tierra","Cristal","Clare","Eliana","Kelli","Eve","Sydnee","Madelynn","Breana","Melina","Arielle","Justice","Toni","Corinne","Maia","Tess","Abbigail","Ciera","Ebony","Maritza","Lena","Lexie","Isis","Aimee","Leticia","Sydni","Sarai","Halie","Alivia","Destiney","Laurel","Edith","Carina","Fernanda","Amya","Destini","Aspen","Nathalie","Paula","Tanya"};
	final static String[] lastNames = new String[]{"Smith","Johnson","Williams","Brown","Jones","Miller","Davis","Garcia","Rodriguez","Wilson","Martinez","Anderson","Taylor","Thomas","Hernandez","Moore","Martin","Jackson","Thompson","White","Lopez","Lee","Gonzalez","Harris","Clark","Lewis","Robinson","Walker","Perez","Hall","Young","Allen","Sanchez","Wright","King","Scott","Green","Baker","Adams","Nelson","Hill","Ramirez","Campbell","Mitchell","Roberts","Carter","Phillips","Evans","Turner","Torres","Parker","Collins","Edwards","Stewart","Flores","Morris","Nguyen","Murphy","Rivera","Cook","Rogers","Morgan","Peterson","Cooper","Reed","Bailey","Bell","Gomez","Kelly","Howard","Ward","Cox","Diaz","Richardson","Wood","Watson","Brooks","Bennett","Gray","James","Reyes","Cruz","Hughes","Price","Myers","Long","Foster","Sanders","Ross","Morales","Powell","Sullivan","Russell","Ortiz","Jenkins","Gutierrez","Perry","Butler","Barnes","Fisher","Henderson","Coleman","Simmons","Patterson","Jordan","Reynolds","Hamilton","Graham","Kim","Gonzales","Alexander","Ramos","Wallace","Griffin","West","Cole","Hayes","Chavez","Gibson","Bryant","Ellis","Stevens","Murray","Ford","Marshall","Owens","McDonald","Harrison","Ruiz","Kennedy","Wells","Alvarez","Woods","Mendoza","Castillo","Olson","Webb","Washington","Tucker","Freeman","Burns","Henry","Vasquez","Snyder","Simpson","Crawford","Jimenez","Porter","Mason","Shaw","Gordon","Wagner","Hunter","Romero","Hicks","Dixon","Hunt","Palmer","Robertson","Black","Holmes","Stone","Meyer","Boyd","Mills","Warren","Fox","Rose","Rice","Moreno","Schmidt","Patel","Ferguson","Nichols","Herrera","Medina","Ryan","Fernandez","Weaver","Daniels","Stephens","Gardner","Payne","Kelley","Dunn","Pierce","Arnold","Tran","Spencer","Peters","Hawkins","Grant","Hansen","Castro","Hoffman","Hart","Elliott","Cunningham","Knight","Bradley","Carroll","Hudson","Duncan","Armstrong","Berry","Andrews","Johnston","Ray","Lane","Riley","Carpenter","Perkins","Aguilar","Silva","Richards","Willis","Matthews","Chapman","Lawrence","Garza","Vargas","Watkins","Wheeler","Larson","Carlson","Harper","George","Greene","Burke","Guzman","Morrison","Munoz","Jacobs","Brien","Lawson","Franklin","Lynch","Bishop","Carr","Salazar","Austin","Mendez","Gilbert","Jensen","Williamson","Montgomery","Harvey","Oliver","Howell","Dean","Hanson","Weber","Garrett","Sims","Burton","Fuller","Soto","McCoy","Welch","Chen","Schultz","Walters","Reid","Fields","Walsh","Little","Fowler","Bowman","Davidson","May","Day","Schneider","Newman","Brewer","Lucas","Holland","Wong","Banks","Santos","Curtis","Pearson","Delgado","Valdez","Pena","Rios","Douglas","Sandoval","Barrett","Hopkins","Keller","Guerrero","Stanley","Bates","Alvarado","Beck","Ortega","Wade","Estrada","Contreras","Barnett","Caldwell","Santiago","Lambert","Powers","Chambers","Nunez","Craig","Leonard","Lowe","Rhodes","Byrd","Gregory","Shelton","Frazier","Becker","Maldonado","Fleming","Vega","Sutton","Cohen","Jennings","Parks","McDaniel","Watts","Barker","Norris","Vaughn","Vazquez","Holt","Schwartz","Steele","Benson","Neal","Dominguez","Horton","Terry","Wolfe","Hale","Lyons","Graves","Haynes","Miles","Park","Warner","Padilla","Bush","Thornton","McCarthy","Mann","Zimmerman","Erickson","Fletcher","McKinney","Page","Dawson","Joseph","Marquez","Reeves","Klein","Espinoza","Baldwin","Moran","Love","Robbins","Higgins","Ball","Cortez","Le","Griffith","Bowen","Sharp","Cummings","Ramsey","Hardy","Swanson","Barber","Acosta","Luna","Chandler","Blair","Daniel","Cross","Simon","Dennis","Connor","Quinn","Gross","Navarro","Moss","Fitzgerald","Doyle","McLaughlin","Rojas","Rodgers","Stevenson","Singh","Yang","Figueroa","Harmon","Newton","Paul","Manning","Garner","McGee","Reese","Francis","Burgess","Adkins","Goodman","Curry","Brady","Christensen","Potter","Walton","Goodwin","Mullins","Molina","Webster","Fischer","Campos","Avila","Sherman","Todd","Chang","Blake","Malone","Wolf","Hodges","Juarez","Gill","Farmer","Hines","Gallagher","Duran","Hubbard","Cannon","Miranda","Wang","Saunders","Tate","Mack","Hammond","Carrillo","Townsend","Wise","Ingram","Barton","Mejia","Ayala","Schroeder","Hampton","Rowe","Parsons","Frank","Waters","Strickland","Osborne","Maxwell","Chan","Deleon","Norman","Harrington","Casey","Patton","Logan","Bowers","Mueller","Glover","Floyd","Hartman","Buchanan","Cobb","French","Kramer","McCormick","Clarke","Tyler","Gibbs","Moody","Conner","Sparks","McGuire","Leon","Bauer","Norton","Pope","Flynn","Hogan","Robles","Salinas","Yates","Lindsey","Lloyd","Marsh","McBride","Owen","Solis","Pham","Lang","Pratt","Lara","Brock","Ballard","Trujillo","Shaffer","Drake","Roman","Aguirre","Morton","Stokes","Lamb","Pacheco","Patrick","Cochran","Shepherd","Cain","Burnett","Hess","Li","Cervantes","Olsen","Briggs","Ochoa","Cabrera","Velasquez","Montoya","Roth","Meyers","Cardenas","Fuentes","Weiss","Hoover","Wilkins","Nicholson","Underwood","Short","Carson","Morrow","Colon","Holloway","Summers","Bryan","Petersen","Mckenzie","Serrano","Wilcox","Carey","Clayton","Poole","Calderon","Gallegos","Greer","Rivas","Guerra","Decker","Collier","Wall","Whitaker","Bass","Flowers","Davenport","Conley","Houston","Huff","Copeland","Hood","Monroe","Massey","Roberson","Combs","Franco","Larsen","Pittman","Randall","Skinner"};
	final static String note = "Test Automation Notes Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";
	private static SecureRandom rand = new SecureRandom();

	protected static final String[] authLevels = new String[] { "IAL2", "LoA3" };
	protected static final String[] authTypes = new String[] { "eAuth" };
	protected static final String[] appNames = new String[] { "TaxPro", "OLA" };
	protected static final String[] transTypes = new String[] { "CAF_DLN" }; // "SDLN" is expired
	protected static final String[] userIdTypes = new String[] { "TIN" };
	protected static final String[] intentIds = new String[] { "1" };
	protected static final String[] formNames = new String[] { "1040", "2048" };
	protected static final String[] intentStmts = new String[] { "This is intent statement 1.", "This is intent statement 2.", "This is intent statement 3." };

	public static final String[] storageLocation = new String[] { "TBD_SL1", "TBD_SL2", "TBD_SL3" };
	protected static final String[] attachmentType = new String[] { "Image", "Document", "Pointer" };
	
	protected static final String nL = "\n";
	public static void display(String text) {
		try {
			if (text != null) {
				String[] lines = text.split(nL);
				for (Integer i = 0; i < lines.length; i++) {
					String line = lines[i];
					logger.info(line);
				}
			}
		}
		catch (Exception e) {}
	}
	public static void displaySplitter() {
		display("--------------------------------------------------");
	}
	public static void displayStartSplitter() {
		display("**************************************************");
	}
	public static void displayEndSplitter() {
		display("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
	}
	public static void displayClassAndFunction(String className, String functionName) {
		displayStartSplitter();
		display("Class: " + className);
		display("Function: " + functionName);
		display("");
	}

	public static <T> T pickRandomValue(T[] data) {
		T output = null;

		if (data != null && data.length == 1) {
			output = data[0];
		}
		else if (data != null && data.length > 1) {
			output = data[rand.nextInt(data.length)];
		}

		return output;
	}
	public static String getCurrentTimestamp() {
		String output = "";

		SimpleDateFormat sdf = new SimpleDateFormat(DateConverter.getStandardFormat());
		output = sdf.format(new Date());

		return output;
	}
	public static String getCurrentTimestampAndMinusDays(int days) {
		String output = "";
		long dayInMillsec = 86400000;
		long currentTime = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat(DateConverter.getStandardFormat());
		output = sdf.format(currentTime -(dayInMillsec * days));

		return output;
	}
	public static String getCurrentTimestampAndAddDays(int days) {
		String output = "";
		long dayInMillsec = 86400000;
		long currentTime = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat(DateConverter.getStandardFormat());
		output = sdf.format(currentTime +(dayInMillsec * days));

		return output;
	}

	// --------------------------------------------------------------------------------
	// Random Data Generation
	public static String generateRandomId(Integer maxDigits) {
		String output = "";

		Integer randomLength = rand.nextInt(maxDigits) + 1;

		String pattern = "";
		for (Integer i = 0; i < randomLength; i++) {
			pattern += "x";
		}

		output = generateRandomId(pattern, false);

		return output;
	}
	public static String generateRandomId(Integer minDigits, Integer maxDigits) {
		String output = "";

		Integer randomLength = 0;//minDigits + rand.nextInt(maxDigits - minDigits) + 1;
		if (minDigits != maxDigits) {
			randomLength = minDigits + rand.nextInt(maxDigits - minDigits) + 1;
			
			// Determine if we want to do this instead.
			//Integer difference = Math.abs(minDigits - maxDigits);
			//randomLength = minDigits + rand.nextInt(difference) + 1;
		}
		else {
			randomLength = minDigits;
		}
		
		//System.out.println("Length: " + randomLength + " for (min: " + minDigits + ", max: " + maxDigits + ")");

		String pattern = "";
		for (Integer i = 0; i < randomLength; i++) {
			pattern += "x";
		}

		output = generateRandomId(pattern, false);

		return output;
	}
	public static String generateIdNotWithin(Integer maxDigits, List<String> BadIds) {
		String output = "";
		
		output = generateRandomId(maxDigits);
		while (BadIds.contains(output)) {
			output = generateRandomId(maxDigits);
		}
		
		return output;
	}
	public static String generateRandomText(Integer maxDigits) {
		String output = "";

		String pattern = "";
		for (Integer i = 0; i < maxDigits; i++) {
			pattern += "x";
		}

		output = generateRandomId(pattern, false);

		return output;
	}
	public static String generateRandomId(String pattern, Boolean includeLetters) {
		String output = "";

		if (pattern == null) {
			pattern = "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx";
		}

		String[] characters = pattern.split("");
		for (Integer i = 0; i < characters.length; i++) {
			String character = characters[i];
			if (character.equals("x") || character.equals("y")) {
				if (includeLetters) {
					output += rand.nextInt(2) == 0 ? (i > 0 ? rand.nextInt(10) : (rand.nextInt(9) + 1)) : String.valueOf((char)((rand.nextInt(26) + 1) + 64));
				}
				else {
					output += i > 0 ? rand.nextInt(10) : (rand.nextInt(9) + 1);
				}
			}
			else {
				output += character;
			}
		}

		return output;
	}
	public static String generateRandomName() {
		String output = "";

		String[] source = rand.nextInt(2) == 0 ? maleNames : femaleNames;

		String randomFirstName = source[rand.nextInt(source.length)];
		String randomLastName = lastNames[rand.nextInt(lastNames.length)];
		output = randomFirstName + " " + randomLastName;

		return output;
	}
	public static String generateRandomEmail(String name) {
		String output = name.replace(" ", ".") + "@FakeDomain.com";
		return output;
	}
	public static String generateRandomNote() {
		String output = "";
		String[] noteWords = note.split(" ");
		Integer randomNoteWords = rand.nextInt(noteWords.length);
		String[] randomNotes = new String[randomNoteWords];

		for (Integer i = 0; i < randomNoteWords; i++) {
			randomNotes[i] = noteWords[i];
		}

		output = String.join(" ", randomNotes) + ".";

		return output;
	}
	public static String generateRandomSampleText(Integer maxLength) {
		// Temporary fix
		maxLength = maxLength > note.length() ? note.length() : maxLength;
		String output = note.substring(0, maxLength);
		return output;
	}
	public static String generateRandomAuthLevel() {
		String output = pickRandomValue(authLevels);
		return output;
	}
	public static String generateRandomAuthType() {
		String output = pickRandomValue(authTypes);
		return output;
	}
	public static String generateRandomAppName() {
		String output = pickRandomValue(appNames);
		return output;
	}
	public static String generateRandomSourceAddress() {
		String output = "";

		output += (rand.nextInt(255) + 1) + ".";
		output += (rand.nextInt(255) + 1) + ".";
		output += (rand.nextInt(255) + 1) + ".";
		output += (rand.nextInt(255) + 1);

		return output;
	}
	public static String generateTransType() {
		String output = pickRandomValue(transTypes);
		return output;
	}
	public static String generateUserIdType() {
		String output = pickRandomValue(userIdTypes);
		return output;
	}
	public static String generateIntentId() {
		String output = pickRandomValue(intentIds);
		return output;
	}
	public static String generateFormName() {
		String output = pickRandomValue(formNames);
		return output;
	}
	public static String generateStorageLocation() {
		String output = pickRandomValue(storageLocation);
		return output;
	}
	public static String generateAttachmentType() {
		String output = pickRandomValue(attachmentType);
		return output;
	}
}
