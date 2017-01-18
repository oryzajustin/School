/**
 * 
 * @author Justin Koh
 * ID: 250837810
 * class ID: 78
 **/
public class Record 
{
	private String data;
	private Key k;
	public Record(Key k, String data)
	{
		this.k = k;
		this.data = data;
	}
	/**
	 * @return the key
	 */
	public Key getKey()
	{
		return k;
	}
	/**
	 * @return the data
	 */
	public String getData()
	{
		return data;
	}
	public void setRecord(Key k, String data)
	{
		this.k = k;
		this.data = data;
	}
}
