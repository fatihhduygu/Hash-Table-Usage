package Proje;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;



public class Arama {
	
	public static int[] hucre=new int[211];
		
//--------------------------------------------------------------------------------------//
	
	public static int ascii(String ad)
	{
		int Toplam = 0;
		int uzunluk=ad.length();
		for(int i=0; i<uzunluk; i++)
		{
			char karakter=ad.charAt(i);
			int ascii=(int) karakter;
			Toplam +=Math.pow((i+1), 4)*ascii;
		}
		return Toplam;
	}
	
//--------------------------------------------------------------------------------------//	
	
	public static void dosyaOku()
	{
		File kayit=new File("kelimeler.txt");
		try{
			FileReader dosyaOku=new FileReader(kayit);
			String kucultme;
			BufferedReader dosya=new BufferedReader(dosyaOku);
			
			while((kucultme=dosya.readLine())!=null){
				kucultme=kucultme.toLowerCase();
				int mod1=0;
				int mod2=0;
				mod1=ascii(kucultme) % 212;
				
				if(hucre[mod1] == 0)
				{
					hucre[mod1]=ascii(kucultme);
				}
				
				else
				{
					for(int i=0; i<hucre.length; i++)
					{
						mod2=((i*i)+mod1) % 212;
						if(hucre[mod2]==0)
						{
							hucre[mod2]=ascii(kucultme);
							break;
						}
					}
				}
			}
			dosya.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
//--------------------------------------------------------------------------------------//
	
	public static void arama(String ad)
	{
		int toplam=ascii(ad);
		int sayac=0;
		for(int i=0; i<hucre.length; i++)
		{
			if(toplam==hucre[i])
			{
				System.out.println("Aradýginiz Kelime Bulunmustur");
				sayac++;
			}
		}
		if(sayac==0)
		{
			System.out.println("Aradiginiz kelime Bulunamamistir!!!\n");
			yerDegistirerekArama(ad);
			eksilterekArama(ad);
			
		}
	}
	
//--------------------------------------------------------------------------------------//	
	
	public static void yerDegistirerekArama(String ad)
	{
		char[] dizi=ad.toCharArray();
		char a=' ';
		int toplam=0;
		
		for(int i=0; i<ad.length()-1; i++)
		{
			a=dizi[i];
			dizi[i]=dizi[i+1];
			dizi[i+1]=a;
			String temp=new String (dizi);
			for(int j=0; j<hucre.length; j++)
			{
				toplam=ascii(temp);
				if(toplam==hucre[j])
				{
					System.out.print(temp);
					System.out.println(" mi demek istediniz ? \n");
				}
			}
			
			dizi=ad.toCharArray();
			
		}
	}
	
//--------------------------------------------------------------------------------------//
	
	public static void eksilterekArama(String ad)
	{
		
		for(int i=0; i<ad.length(); i++)
		{
			char[] dizi=ad.toCharArray();
			int toplam=0;
			for(int j=i; j<ad.length()-1; j++)
			{
				dizi[j]=dizi[j+1];
				dizi[j+1]='\0';
			}
			String temp=new String(dizi);
			for(int j=0; j<hucre.length; j++)
			{
				toplam=ascii(temp);
				if(toplam==hucre[j])
				{
					System.out.println("\nyoksa \n");
					System.out.print(temp);
					System.out.println(" mi demek istediniz ?");
				}
			}
		}
	}
	
//--------------------------------------------------------------------------------------//	
	
	public static void main(String[] args) {
		
		Arama ara=new Arama();
		Scanner oku=new Scanner(System.in);
		System.out.println("          _______  ______ _______ _______ _______      _______  _____  _______  _____   ______ _     _");
		System.out.println("          |_____| |_____/ |_____| |  |  | |_____|      |  |  | |     |    |    |     | |_____/ |     |");
		System.out.println("          |     | |    \\_ |     | |  |  | |     |      |  |  | |_____|    |    |_____| |    \\_ |_____|");
		System.out.println(" ________________________________________________________________________________________________________________");	
		System.out.println("|                                                                                                                |");
		System.out.print("  ");
		String ad=oku.nextLine();
		System.out.println("|________________________________________________________________________________________________________________| \n");
		dosyaOku();
		arama(ad);
		
		
		
		
		
		
		
		
		
		
	}

}
