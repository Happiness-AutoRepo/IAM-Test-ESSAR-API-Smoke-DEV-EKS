package com.essar.jsongenerator.dataenum;

import com.aventstack.customreports.Status;
import com.essar.utils.CommonUtils;

public class TokenValues extends CommonUtils {

	public static String getValidToken() {
		return "validToken";
	}

	public static String getEmptyToken() {
		reporter.log(Status.INFO, "Generating empty token");
		return "";
	}

	public static Object getNullToken() {
		reporter.log(Status.INFO, "Generating null token");
		return null;
	}

	public static String getExpiredToken() {
		reporter.log(Status.INFO, "Generating expired token");
		return "1HdY9xubSd7X9VzGOwRo/AQQ8yHU8U80iKTBYyXs1gYv6zXB01Vwd9LcPE+vtVEYu3is/ZFFghi2uEEUJF4Bu3OsxzobR3ge4+C00xgpOzzGswZqKL4iVE8f/fbUmhXjoddNdebcPCRVKNnZT+BhcavXMuzZpobNJWwSmXdi564OfoCt68cc2aT8wvouyHRuVBDQXUEDcZ+t6UurlZ/NNz+3bhEbegOvk4zT7SVm6q1kTHYix3PD/8oTq6i0/hQnGrggDk/HmRwF1Xsq76Mszytq5XaDsQdFx1BFtrsJWyL3uDg+82Ky/CUJrJ7maWjZOxgzcQBiXUOfGHww0r0DqAvzsgbcFOdcmS3pS/Xip8pQnPVyZThwcejeIxihNDijYOYHpIutvFJxMj2CPxlQaBZpVBD7VkfqMv8/kWAcQvwn/3jA/PrDoMbCBfCSXlYd0qH0Yd6o19kaEvXO5Id806zDxV0joRwX07azC4eEkHWy5XlzaouwkKT7pg2YFoAchPxs7LikSJ8BxexY1yW3HvE+TDNIyu3MVLTsmdUIR35dmsozPgGbf05SIlV8wws6KnLLVP0xbXMmxJjllVtqkovtkhi0TyA0L0PSqWvdnxeJ4D//tNHyj7YoYFN8k4jOdOJJnYbmr/Yl+8U4P62pCkVaxBRans/NSbWH6aVtzPQ/d6NXt9rarU4p455AVFd4DjUVrBJjSytIO4BGU0NuY2t33Yzm+8+1RT9msfHcCKfk/U9pDvwyXZrn/mcs+PEGo6+LIMiz/SJSu0E8b/dVPPOIWXrcDOa/w+/O9TCpeLtmuTAEXK2qw5Eo6FCE/m67gVoptNHD1Tn1WlZtS2Wp23uJ/jmbKYd1ff/v60PliBCLq4ukMr0ERpKzcGYqMQF8XrdGOPDHgUhBWbNWqNJjZC9HkXoS4PR0fwvoHUQ6BSiprJy86qlNeFt0C2J1MFDsrekKSinWTU75uTtyftdCqDa52PcJPXauHa9wPM3v+xUYoZDCM+5fdSE3O7X7oNvPfKEXO6HHVPosrb1r6Ww+w3y/v0MJiworGi4eyZzZZ4SYvCd4aGuYW7ysRl0bL54xMnOh0SKVLKosG9+PYo0pPQmKvi6dkFsCW2uhbDOBKBElPk3VRdF/+2c90UxbH+/Q31nQ+ULqwAW07MvLaJftJg==";
	}

	public static String getInvalidLengthToken() {
		reporter.log(Status.INFO, "Generating invalid length token");
		return "1HdY9xubSd7X9VzGOwRo/AQQ8yHU8U80iKTBYyXs1gYv6zXB01Vwd9LcPE+vtVEYu3is/ZFFghi2uEEUJF4Bu3OsxzobR3ge4+C00xgpOzzGswZqKL4iVE8f/fbUmhXjoddNdebcPCRVKNnZT+BhcavXMuzZpobNJWwSmXdi564OfoCt68cc2aT8wvouyHRuVBDQXUEDcZ+t6UurlZ/NNz+3bhEbegOvk4zT7SVm6q1kTHYix3PD/8oTq6i0/hQnGrggDk/HmRwF1Xsq76Mszytq5XaDsQdFx1BFtrsJWyL3uDg+82Ky/CUJrJ7maWjZOxgzcQBiXUOfGHww0r0DqAvzsgbcFOdcmS3pS/Xip8pQnPVyZThwcejeIxihNDijYOYHpIutvFJxMj2CPxlQaBZpVBD7VkfqMv8/kWAcQvwn/3jA/PrDoMbCBfCSXlYd0qH0Yd6o19kaEvXO5Id806zDxV0joRwX07azC4eEkHWy5XlzaouwkKT7pg2YFoAchPxs7LikSJ8BxexY1yW3HvE+TDNIyu3MVLTsmdUIR35dmsozPgGbf05SIlV8wws6KnLLVP0xbXMmxJjllVtqkovtkhi0TyA0L0PSqWvdnxeJ4D//tNHyj7YoYFN8k4jOdOJJnYbmr/Yl+8U4P62pCkVaxBRans/NSbWH6aVtzPQ/d6NXt9rarU4p455AVFd4DjUVrBJjSytIO4BGU0NuY2t33Yzm+8+1RT9msfHcCKfk/U9pDvwyXZrn/mcs+PEGo6+LIMiz/SJSu0E8b/dVPPOIWXrcDOa/w+/O9TCpeLtmuTAEXK2qw5Eo6FCE/m67gVoptNHD1Tn1WlZtS2Wp23uJ/jmbKYd1ff/v60PliBCLq4ukMr0ERpKzcGYqMQF8XrdGOPDHgUhBWbNWqNJjZC9HkXoS4PR0fwvoHUQ6BSiprJy86qlNeFt0C2J1MFDsrekKSinWTU75uTtyftdCqDa52PcJPXauHa9wPM3v+xUYoZDCM+5fdSE3O7X7oNvPfKEXO6HHVPosrb1r6Ww+w3y/v0MJiworGi4eyZzZZ4SYvCd4aGuYW7ysRl0bL54xMnOh0SKVLKosG9+PYo0pPQmKvi6dkFsCW2uhbDOBKBElPk3VRdF/+2c90UxbH+/Q31nQ+ULqwAW07MvLaJftJg==1234567889";
	}

	public static String getInvalidToken() {
		reporter.log(Status.INFO, "Generating invalid token");
		return "invalidToken";
	}
}