package buySell.database;

public class Queries {
	
 private String nil="nil";
 	//return a query for getting item specifics
	public String qGetItems(String iname, String manf, String iclass){
		String s="Select * from dbo.Items where 1=1";
		
		if (!(iname.equals(nil))) {
				s=s+" AND Item_Name='"+iname+"'";
		}
		if (!(manf.equals(nil))) {
			s=s+" AND Manf='"+manf+"'";
		}
		if (!(iclass.equals(nil))) {
			s=s+" AND Item_Class='"+iclass+"'";
		}
		return s;
	}
	//you should probably be able to tell by the name
	public String qGetUsers(String uname, String vid, String pw){
		String s="Select Username, VendorID from Users where 1=1";
		if(!(uname.equals(nil))){
			s=s+" AND UserName='"+uname+"'";
		}
		if(!(vid.equals(nil))){
			s=s+" AND VendorID="+Integer.parseInt(vid);
		}
		if(!(pw.equals(nil))){
			s=s+" AND password='"+pw+"'";
		}
		return s;
	}
	public String qGetVendors(String name, String Address, String Current, String Lic){
		String s="Select * from Vendors where 1=1";
		if (!(name.equals(nil))){
			s=s+" AND Name='"+name+"'";
		}
		if(!(Address.equals(nil))){
			s=s+" AND Address = '"+Address+"'";
		}
		if(!(Current.equals(nil))){
			s=s+" AND IsCurrent="+Integer.parseInt(Current);
		}
		if(!(Lic.equals(nil))){
			s=s+ " AND Licenses ="+Integer.parseInt(Lic);
		}
		return s;
	}
	public String qGetWTBFull(String iname, String manf, String iclass, String vname, String vadd, String vqty, String price, String need, String type){
		String s="Select * from wtb_full where 1=1";
		if(!(iname.equals(nil))){
			s=s+" AND Item_Name='"+iname+"'";
		}
		if(!(manf.equals(nil))){
			s=s+" AND manf='"+manf+"'";
		}
		if(!(iclass.equals(nil))){
			s=s+" AND Item_Class='"+iclass+"'";
		}
		if(!(vname.equals(nil))){
			s=s+" AND name='"+vname+"'";
		}
		if(!(vadd.equals(nil))){
			s=s+ "AND Address='"+vadd+"'";
		}
		if(!(vqty.equals(nil))){
			s=s+" AND qty="+Integer.parseInt(vqty);
		}
		if(!(price.equals(nil))){
			s=s+" AND price="+Float.parseFloat(price);
		}
		if(!(need.equals(nil))){//'12/12/2012' format
			s=s+" AND Convert(varchar(11), needby ,101)='"+need+"'";
		}
		if(!(type.equals(nil))){
			s=s+" AND type='"+type+"'";
		}
		
		return s;
	}
	public String qGetWTSFull(String iname, String manf, String iclass, String vname, String vadd, String vqty, String price, String avail_date){
		String s="Select * from wts_full where 1=1";
		if(!(iname.equals(nil))){
			s=s+" AND Item_Name='"+iname+"'";
		}
		if(!(manf.equals(nil))){
			s=s+" AND manf='"+manf+"'";
		}
		if(!(iclass.equals(nil))){
			s=s+" AND Item_Class='"+iclass+"'";
		}
		if(!(vname.equals(nil))){
			s=s+" AND name='"+vname+"'";
		}
		if(!(vadd.equals(nil))){
			s=s+ "AND Address='"+vadd+"'";
		}
		if(!(vqty.equals(nil))){
			s=s+" AND qty="+Integer.parseInt(vqty);
		}
		if(!(price.equals(nil))){
			s=s+" AND price="+Float.parseFloat(price);
		}
		if(!(avail_date.equals(nil))){//'12/12/2012' format
			s=s+" AND Convert(varchar(11), needby ,101)='"+avail_date+"'";
		}
		return s;
	}
	public String qInsertItem(String ID,String iname, String manf, String iclass){
		String s="insert into items values("+Integer.parseInt(ID)+",'"+iname+"'"+
				",'"+manf+"'"+",'"+iclass+"')";
		return s;
	}
	
	public String qInsertVendor(String ID,String name, String Address,  String Lic, String Current){
		
		String s="insert into vendors values("+Integer.parseInt(ID)+",'"+name+"','"+Address+
				"',"+Integer.parseInt(Lic)+","+Integer.parseInt(Current)+")";
		return s;
	}
	public String qInsertUser(String ID,String uname, String vid, String pw){
		String s=null;
		s="insert into users values ("+Integer.parseInt(ID)+",'"+uname+"',"+Integer.parseInt(vid)+",'"+pw+"')";
		return s;
	}
	public String qInsertWTB(String ID, String ItemID, String QTY, String Type, String Needby, String CustID, String price){
		String s=null;
		s="insert into WTB values("+Integer.parseInt(ID)+","+Integer.parseInt(ItemID)+","+Integer.parseInt(QTY)+",'"+Type+"','"+Needby+"',"+Integer.parseInt(CustID)+","+Float.parseFloat(price)+")";
		return s;
	}
	public String qInsertWTS(String ID, String ItemID, String VendorID, String QTY, String Price, String Avail_date){
		String s=null;
		s="insert into wts values("+Integer.parseInt(ID)+","+Integer.parseInt(ItemID)+","+Integer.parseInt(VendorID)+","+Integer.parseInt(QTY)+
				","+Float.parseFloat(Price)+",'"+Avail_date+"')";
		return s;
	}
	public String qUpdateItems(String iname, String manf, String iclass, String key){
		String s=null;
		s="update items set item_name='"+iname+"', manf='"+manf+"', item_class='"+iclass+"' where item_name='"+key+"'";
		return s;
	}
	public String qUpdateUsers(String uname, String vid, String pw, String key){
		String s=null;
		s="update users set username='"+uname+"', vendorid="+Integer.parseInt(vid)+", password='"+pw+"' where username='"+key+"'";
		return s;
	}
	public String qUpdateVendors(String name, String Address, String Current, String Lic, String key){
		String s=null;
		s="update vendors set name='"+name+"', Address='"+Address+"', Iscurrent="+Integer.parseInt(Current)+", Licenses="+Integer.parseInt(Lic)+" where Name='"+key+"'";
		return s;
	}
	public String qUpdateWTB(String ItemID, String QTY, String Type, String Needby, String CustID, String price, String key){
		String s=null;
		s="update WTB set ItemID="+Integer.parseInt(ItemID)+",QTY="+Integer.parseInt(QTY)+",Type='"+Type+"', Needby='"+Needby+"',CustID="+Integer.parseInt(CustID)+",price="+Float.parseFloat(price)+" where ID="+Integer.parseInt(key);
		return s;
	}
	public String qUpdateWTS(String ItemID, String VendorID, String QTY, String Price, String Avail_date, String key){
		String s=null;
		s="update WTS set ItemID="+Integer.parseInt(ItemID)+", VendorID="+Integer.parseInt(VendorID)+",QTY ="+Integer.parseInt(QTY)+",price="+Float.parseFloat(Price)+",Avail_date='"+Avail_date+"' where ID ="+Integer.parseInt(key);
		return s;
	}
	public String qDeleteItems(String key){
		String s=null;
		s="delete from Items where Item_name='"+key+"'";
		return s;
	}
	public String qDeleteUsers(String key){
		String s=null;
		s="delete from users where username='"+key+"'";
		return s;
	}
	public String qDeleteVendors(String key){
		String s=null;
		s="delete from Vendors where name='"+key+"'";
		return s;
	}
	public String qDeleteWTB(String key){
		String s=null;
		s="delete from WTB where ID="+Integer.parseInt(key);
		return s;
	}
	
	public String qDeleteWTS(String key){
		String s=null;
		s="delete from WTS where ID="+Integer.parseInt(key);
		return s;
	}
	
}
