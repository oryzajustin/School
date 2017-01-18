/**
 * @author Justin Koh
 * ID: 250837810
 * class ID: 78
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
public class UI 
{
	public static void main(String[] args) throws IOException, DictionaryException, MultimediaException
	{
		OrderedDictionary dictionary = new OrderedDictionary();
		String file = args[0];
		String mid = ".mid";
		String wav = ".wav";
		String jpg = ".jpg";
		String gif = ".gif";
		int type = 0;
		BufferedReader input = new BufferedReader(new FileReader(file));
		String fileLine1 = input.readLine();
		String fileLine2 = input.readLine();
		while(fileLine1 != null)
		{
			// if the data has .jpg or .gif type is 3
			if(fileLine2.toLowerCase().indexOf(jpg) > 0 || fileLine2.toLowerCase().indexOf(gif) > 0)
			{
				type = 3;
				Key key = new Key(fileLine1.toLowerCase(), type);
				Record record = new Record(key, fileLine2.toLowerCase());
				dictionary.insert(record);
			}
			// if the data has .mid or .wav the type is 2
			else if(fileLine2.toLowerCase().indexOf(mid) > 0 || fileLine2.toLowerCase().indexOf(wav) > 0)
			{
				type = 2;
				Key key = new Key(fileLine1.toLowerCase(), type);
				Record record = new Record(key, fileLine2.toLowerCase());
				dictionary.insert(record);
			}
			// if the 
			else
			{
				type = 1;
				Key key = new Key(fileLine1.toLowerCase(), type);
				Record record = new Record(key, fileLine2.toLowerCase());
				dictionary.insert(record);
			}
			fileLine1 = input.readLine();
			fileLine2 = input.readLine();
		}
		input.close();
		boolean terminate = false;
		while(terminate == false) // while the user is still inputing commands
		{
			StringReader keyboard = new StringReader();
			String line = keyboard.read("Enter next command: ");
			StringTokenizer string = new StringTokenizer(line);
			String userRequest = null;
			if(string.hasMoreTokens())
			{
				userRequest = string.nextToken().toLowerCase();
			}
			if(userRequest.equals("search") && string.hasMoreTokens())
			{
				int ktype = 0;
				String word = string.nextToken().toLowerCase();
				for(int i = 1; i < 4; i++)
				{
					Key key = new Key(word, i);
					if(dictionary.find(key) != null)
					{
						ktype = i;
						break;
					}
				}
				Key realKey = new Key(word, ktype);
				Record rec = dictionary.find(realKey);
				if(ktype == 1)
				{
					System.out.println(rec.getData());
				}
				else if(ktype == 2)
				{
					try
					{
					SoundPlayer sound = new SoundPlayer();
					sound.play(rec.getData());
					}
					catch (MultimediaException e) 
					{
					    System.out.println(e.getMessage());
					}
				}
				else if(ktype == 3)
				{
					try
					{
					PictureViewer picture = new PictureViewer();
					picture.show(rec.getData());
					}
					catch (MultimediaException e) 
					{
					    System.out.println(e.getMessage());
					}
				}
				else
				{
					System.out.println("The record does not exist.");
				}
			}
			else if(userRequest.equals("remove") && string.hasMoreTokens())
			{
				String word = string.nextToken().toLowerCase();
				int keyType = Integer.parseInt(string.nextToken());
				Key realKey = new Key(word, keyType);
				if(dictionary.find(realKey) == null)
				{
					System.out.println("No such record exists.");
				}
				else
				{
					dictionary.remove(realKey);
				}
				
			}
			else if(userRequest.equals("insert") && string.hasMoreTokens())
			{
				String word = string.nextToken().toLowerCase();
				int keyType = Integer.parseInt(string.nextToken());
				String data = string.nextToken();
				Key realKey = new Key(word, keyType);
				Record rec = new Record(realKey, data);
				dictionary.insert(rec);
			}
			else if(userRequest.equals("next") && string.hasMoreTokens())
			{
				String word = string.nextToken().toLowerCase();
				int keyType = Integer.parseInt(string.nextToken());
				Key k = new Key(word, keyType);
				if(dictionary.successor(k).getKey() == null)
				{
					System.out.println("There is no larger key in this dictionary.");
				}
				else
				{
					System.out.println("(" + dictionary.successor(k).getKey().getWord() + "," + dictionary.successor(k).getKey().getType() + ")");
				}

			}
			else if(userRequest.equals("prev") && string.hasMoreTokens())
			{
				String word = string.nextToken().toLowerCase();
				int keyType = Integer.parseInt(string.nextToken());
				Key k = new Key(word,keyType);
				if(dictionary.predecessor(k).getKey() == null)
				{
					System.out.println("There is no smaller key in this dictionary.");
				}
				else
				{
					System.out.println("(" + dictionary.predecessor(k).getKey().getWord() + "," + dictionary.predecessor(k).getKey().getType() + ")");
				}
			}
			else if(userRequest.equals("first") && string.hasMoreTokens())
			{
				System.out.println("(" + dictionary.smallest().getKey().getWord() + "," + dictionary.smallest().getKey().getType() + ")");
			}
			else if(userRequest.equals("last") && string.hasMoreTokens())
			{
				System.out.println("(" + dictionary.largest().getKey().getWord() + "," + dictionary.largest().getKey().getType() + ")");
			}
			else if(userRequest.equals("end"))
			{
				terminate = true;
				System.out.println("The program will now exit.");
				System.exit(0);
			}
			else
			{
				System.out.println("invalid input");
			}
		}
	}
}