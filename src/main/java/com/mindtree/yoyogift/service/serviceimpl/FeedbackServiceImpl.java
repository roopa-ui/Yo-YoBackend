package com.mindtree.yoyogift.service.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mindtree.yoyogift.dto.FeedBackDto;
import com.mindtree.yoyogift.entity.FeedBack;
import com.mindtree.yoyogift.entity.Product;
import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.repository.FeedBackRepository;
import com.mindtree.yoyogift.repository.ProductRepository;
import com.mindtree.yoyogift.repository.UserRepository;
import com.mindtree.yoyogift.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	FeedBackRepository feedbackRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	UserRepository userRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<FeedBackDto> getFeedback() {
		List<FeedBack> feedback = feedbackRepository.findAll();
		List<FeedBackDto> feedbacks = feedback.stream().map(entity -> convertEntityToDto(entity))
				.collect(Collectors.toList());
		return feedbacks;
	}
	@Override
	public FeedBackDto addFeedback(FeedBackDto feedback, int productId, String mailId) {
		Product product = productRepository.findById(productId).get();
		User user = userRepository.findByMailId(mailId);
		FeedBack feedbacks = convertDtoToEntity(feedback);
		Optional<FeedBack> feedbackExists = feedbackRepository.getUserFeedback(mailId, productId);
		if (feedbackExists.isPresent()) {
			feedbackExists.get().setFeedbackStarRating(feedbacks.getFeedbackStarRating());
			feedbackExists.get().setFeedbackComment(feedbacks.getFeedbackComment());
			return convertEntityToDto(feedbackRepository.saveAndFlush(feedbackExists.get()));
		} else {
			feedbacks.setProduct(product);
			feedbacks.setUser(user);
			return convertEntityToDto(feedbackRepository.save(feedbacks));
			
		}

	}

	private FeedBackDto convertEntityToDto(FeedBack feedback) {
		return modelMapper.map(feedback, FeedBackDto.class);

	}

	private FeedBack convertDtoToEntity(FeedBackDto feedback) {
		return modelMapper.map(feedback, FeedBack.class);
	}

}
