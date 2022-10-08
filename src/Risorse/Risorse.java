package Risorse;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.imageio.ImageIO;

import Model.DAO.TagliaDAO;
import Model.bean.OrdineTestataBean;
import Model.bean.TagliaBean;

public class Risorse{
	
	public static String encrypt(String string) throws NoSuchAlgorithmException {
		MessageDigest digest;
		digest = MessageDigest.getInstance("SHA3-256");
		byte[] messageDigest= digest.digest(
		string.getBytes(StandardCharsets.UTF_8));
		return bytesToHex(messageDigest);
	}

	private static String bytesToHex(byte[] hash) {
	    StringBuilder hexString = new StringBuilder(2 * hash.length);
	    for (int i = 0; i < hash.length; i++) {
	        String hex = Integer.toHexString(0xff & hash[i]);
	        if(hex.length() == 1) {
	            hexString.append('0');
	        }
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
	
	public static void saveAndEncrypt(String image, String path) throws IOException{
		File imageFile=new File(path);
		image=image.substring(5, image.length()-2);
		String base64image=image.split(",")[1];
		
		ByteArrayInputStream bis = new ByteArrayInputStream(Base64.getDecoder().decode(base64image));
		BufferedImage imageIO = ImageIO.read(bis);
		bis.close();
		ImageIO.write(imageIO, "jpg",imageFile);
	}
	
	public static void sortTaglie(ArrayList<TagliaBean> list) throws SQLException {
		
		List<TagliaBean> newTaglie=new ArrayList<>();
		TagliaDAO tagliaDAO=new TagliaDAO();
		String[] misure= {"XXXS","XXS","XS","S","M","L","XL","XXL","XXXL"};
		for(String misura: misure) {
			TagliaBean tagliaBean=tagliaDAO.doRetrieveByTaglia(misura);
			if(list.contains(tagliaBean))	
			{
				tagliaBean.setQuantita(list.get(list.indexOf(tagliaBean)).getQuantita());
				list.remove(tagliaBean);
				newTaglie.add(tagliaBean);
			}
		}

		Collections.sort(list,new Comparator<TagliaBean>() {
			public int compare(TagliaBean o1, TagliaBean o2) {
				return o1.getTaglia().compareTo(o2.getTaglia());
				
			}
		});
		list.addAll(newTaglie);
	}
	
	public static void sortOrdiniTestata(ArrayList<OrdineTestataBean> list) {
		Collections.sort(list,new Comparator<OrdineTestataBean>() {
			public int compare(OrdineTestataBean o1, OrdineTestataBean o2) {
				
				int i=o1.getData().compareTo(o2.getData());
				if(i==0)
				{
					return o1.getOra().compareTo(o2.getOra());
				}
				return i;
			}
		});
	}
}
