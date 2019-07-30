package com.app.cdac.acts.mrcomforty.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.cdac.acts.mrcomforty.dao.BookingDao;
import com.app.cdac.acts.mrcomforty.dao.CustomerDao;
import com.app.cdac.acts.mrcomforty.dao.ServiceProviderDao;
import com.app.cdac.acts.mrcomforty.pojos.Booking;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingDao dao;

	@Autowired
	private CustomerDao cusDao;
	@Autowired
	private ServiceProviderDao providerDao;

	@Override
	public int saveBooking(Booking book) {
		return dao.save(book).getBookingId();

	}

	@Override
	public Booking getBookingById(int booking_id) {
		return dao.findById(booking_id).get();

	}

	@Override
	public void UpdateBookingHours(int hours, int booking_id) {
		dao.setBookingWorkingHours(hours, booking_id);

	}

	@Override
	public void UpdateBookingStatus(int status, int booking_id) {
		dao.setBookingWorkingStatus(status, booking_id);

	}

	@Override
	public Booking getBookingByCustomerId(int customer_id) {
		return dao.findById(customer_id).get();
	}

	@Override
	public Booking getBookingByServiceProId(int serviceprovider_id) {
		return dao.findById(serviceprovider_id).get();
	}

	@Override
	public int getServiceProviderIdByBookingId(int bookingId) {
		return dao.findById(bookingId).get().getServiceProviderId();
	}

	@Override
	public Integer getAllServiceProvidersIdNotBookedAtDate(int serviceProviderId, Date date) {
		return dao.getAllServiceProvidersIdNotBookedAtDate(serviceProviderId, date);

	}

	@Override
	public List<Booking> getCurrentBookingDetailsByCustomerId(int id) {
		return dao.getCurrentBookingDetailsByCustomerId(id);
	}

	@Override
	public List<Booking> getHistoryOfBookingDetailsByCustomerId(int id) {
		return dao.getHistoryOfBooking(id);
	}

	@Override
	public String getProviderName(int id) {
		String Pname = providerDao.getNameByProviderName(id);
		return Pname;
	}

	@Override
	public String getProviderNumber(int id) {
		String Pnumber = providerDao.getNumberByProviderId(id);
		return Pnumber;
	}

	@Override
	public int getCustomerId(int id) {
		int cust_id = dao.getCustomerId(id);
		return cust_id;
	}

	@Override
	public int getProviderId(int id) {
		int provider_id = dao.getProviderId(id);
		return provider_id;
	}

	@Override
	public String getCustomerName(int id) {
		String name = cusDao.getNameByCustomerId(id);
		return name;
	}

	@Override
	public String getCustomerNumber(int id) {
		String number = cusDao.getNumberByCustomerId(id);
		return number;
	}
	@Override
	public void sendcust(int bookingId)
	{
		int providerid = getProviderId(bookingId);
		int cust_id = getCustomerId(bookingId);
		String pName=getProviderName(providerid);
		String pNo=getProviderNumber(providerid);
		String phnNo = getCustomerNumber(cust_id);
		String cName= getCustomerName(cust_id);
		String message="Hello\n" + cName + " your Booking has  been confirmed\nService Provide name is:" + pName
				+ "\nContact No.:" + pNo + "\nRegards \nTeam Mr. Comforty ";
		sendSms(message,phnNo);
		
	}
	@Override
	public void sendProv(int bookingId)
	{
		int providerid = getProviderId(bookingId);
		int cust_id = getCustomerId(bookingId);
		String pName=getProviderName(providerid);
		String pNo=getProviderNumber(providerid);
		String phnNo = getCustomerNumber(cust_id);
		String cName= getCustomerName(cust_id);
		String message="Hello\n" + pName + " your services have been Booked  by \nCustomer name is:" + cName
				+ "\nContact No.:" + phnNo + "\nRegards \nTeam Mr. Comforty ";
		sendSms(message,pNo);
	}
	@Override
	public String sendSms(String mess,String number) {
		try {
			
			
			String apiKey = "apikey=" + "XPBVq0sU5AU-1cFtwwFvdp2BADxcUQrVJX0E4zNGcS";
			String message = "&message="+mess;
			String sender = "&sender=" + "TXTLCL";
			System.out.println(message);
			String numbers = "&numbers=" + number;
			System.out.println("\n\n\n\n\n\n\nthis is number " + numbers);

			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS " + e);
			return "Error " + e;
		}
	}
}
