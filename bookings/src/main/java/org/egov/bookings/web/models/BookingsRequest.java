package org.egov.bookings.web.models;

import java.util.Map;
import javax.validation.Valid;
import org.egov.bookings.model.BookingsModel;
import org.egov.bookings.model.user.OwnerInfo;
import org.egov.common.contract.request.RequestInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BookingsRequest {

	@JsonProperty("Booking")
	@Valid
	private BookingsModel bookingsModel;

	@JsonProperty("RequestInfo")
	private RequestInfo requestInfo;
	
	@JsonProperty("UrlData")
	private Map<String, String> urlData;
	
	private OwnerInfo user;
	
}
