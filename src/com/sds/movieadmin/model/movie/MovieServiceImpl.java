package com.sds.movieadmin.model.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.movieadmin.domain.Movie;
import com.sds.movieadmin.exception.MovieException;

@Service
public class MovieServiceImpl implements MovieService{

	@Autowired
	private MovieDAO movieDAO;
	
	public void regist(Movie movie) throws MovieException {
		movieDAO.insert(movie);
	}

}
