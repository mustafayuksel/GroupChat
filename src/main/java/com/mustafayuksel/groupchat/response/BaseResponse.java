package com.mustafayuksel.groupchat.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BaseResponse {

	@ApiModelProperty
	private Boolean isSuccess;
	@ApiModelProperty
	private String errorMessage;

	public Boolean getSuccess() {
		return isSuccess;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public BaseResponse(String errorMessage, Boolean isSuccess) {
		this.errorMessage = errorMessage;
		this.isSuccess = isSuccess;
	}

	public BaseResponse() {
	}
}