package com.mindtree.yoyogift.service.serviceimpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.yoyogift.dto.GiftDto;
import com.mindtree.yoyogift.dto.ProductDto;
import com.mindtree.yoyogift.entity.Gift;
import com.mindtree.yoyogift.entity.Product;
import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.exception.serviceexception.YoYoGiftServiceException;
import com.mindtree.yoyogift.exception.utility.ErrorConstant;
import com.mindtree.yoyogift.repository.GiftRepository;
import com.mindtree.yoyogift.repository.ProductRepository;
import com.mindtree.yoyogift.repository.UserRepository;
import com.mindtree.yoyogift.service.GiftService;

@Service
public class GiftServiceImpl implements GiftService {
	@Autowired
	GiftRepository giftRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ProductRepository productRepository;

	private ModelMapper modelMapper = new ModelMapper();

	static String senderName;

	@Override
	public String generateRandomCode() {

		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";

		// create StringBuffer size of AlphaNumericString
		StringBuilder stringBuilder = new StringBuilder(25);

		for (int i = 1; i < 25; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			char index;
			if (i % 5 == 0) {
				index = '-';
				// add Character one by one in end of stringbuilder
				stringBuilder.append(index);
			} else {
				index = (char) (AlphaNumericString.length() * Math.random());
				// add Character one by one in end of stringbuilder
				stringBuilder.append(AlphaNumericString.charAt(index));
			}

		}

		return stringBuilder.toString();
	}

	@Override
	public boolean sendMailToRecipient(String recipientMailId, String redeemCode) {
		final String userMailId = "yoyogiftshophw@gmail.com";
		final String password = "yoyogiftshopproject";
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userMailId, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userMailId));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientMailId));

			message.setSubject("Gift From " + senderName);

			String mailContent = "<html><head></head><body><h1>You Received A Gift!</h1><img src='https://cuteomatic.com/wp-content/uploads/2014/11/Good-Christmas-Gifts-For-Your-Boyfriend.jpg' height=500px width=800px><br>";
			mailContent += "<b>Please login to YOYO GIFT Website and redeem gift!</b><br>";
			mailContent += "<font color=red>redeem using code &nbsp;" + redeemCode + "</font></body></html>";
			message.setContent(mailContent, "text/html");

			Transport.send(message);

			System.out.println("Mail Sent Successfully");
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public GiftDto addReceivedUser(String mailId, String code) throws YoYoGiftServiceException {

		int match = 0;
		int status = 0;
		List<Gift> gifts = new ArrayList<Gift>();
		Gift giftSaved = new Gift();
		giftRepository.findAll().forEach(gifts::add);
		User user = userRepository.findByMailId(mailId);
		System.out.println("gift");
		for (Gift gift : gifts) {
			if (gift.getRedeemCode().equals(code)) {
				if (gift.isStatus() == false) {
					gift.setReceiveGiftUser(user);
					gift.setStatus(true);
					giftSaved = giftRepository.save(gift);
					status++;
				}
				match++;
			}
		}
		if (match == 0) {
			throw new YoYoGiftServiceException(ErrorConstant.INVALIDCODE);
		}
		if (status == 0) {
			throw new YoYoGiftServiceException(ErrorConstant.GIFTALREADYREDEEMED);
		}

		return convertEntityToDto(giftSaved);
	}

	private GiftDto convertEntityToDto(Gift gift) {
		return modelMapper.map(gift, GiftDto.class);

	}

	private Gift convertDtoToEntity(GiftDto gift) {
		return modelMapper.map(gift, Gift.class);
	}

	@Override

	public GiftDto postGift(ProductDto productDto, String senderMailId, String receiverMailId) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDate now = LocalDate.now();
		Gift gift = new Gift();
		Gift giftPosted = new Gift();
		Optional<Product> product = productRepository.findById(productDto.getProductId());

		User user = userRepository.findByMailId(senderMailId);
		int updatedUserCash = user.getYoyoCash() - productDto.getYoyoPrice();
		user.setYoyoCash(updatedUserCash);
		userRepository.save(user);
		gift.setGiftDate(now);
		gift.setStatus(false);

		gift.setRedeemCode(generateRandomCode());
		gift.setSentGiftUser(user);
		gift.setProduct(product.get());
		giftPosted = giftRepository.save(gift);
		senderName = user.getUserName();
		sendMailToRecipient(receiverMailId, gift.getRedeemCode());
		return convertEntityToDto(giftPosted);
	}
}
