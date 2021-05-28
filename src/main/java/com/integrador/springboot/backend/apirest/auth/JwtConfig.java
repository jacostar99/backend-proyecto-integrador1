package com.integrador.springboot.backend.apirest.auth;


public class JwtConfig {
	public static final String LLAVE_SECRETA ="alguna.clave.secreta.12345"; 
	
	
	public static final String RSA_PRIVADA="-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEpQIBAAKCAQEAyUiBQZPMXtFI1cPsna2CsrVpYkCyZQfe1XE/K3T/tNdGSvzD\r\n" + 
			"h25gnhf2fvbMZcd2YOg1gksp16ImOHAwGtFK0x2S8og/akGayB8x9/6OOGgZpdEl\r\n" + 
			"hngmnLas48nun/jpF94mubBNPcPbiYmAVlsJgbVS6tCdBWVVDjhAtqoRLEt/DFHo\r\n" + 
			"S6fTui0AmWmhqCmuXn0UGyqjYCNpGtqL2LL/mzsHREG7ym2q9HUEBpu+PLVx+kHL\r\n" + 
			"tSo7rWPWxFgnGKMHaRoOCB7zsVjPkR5K852mRaPlUQwMuB4UVoRN2KN1V+JKaE4y\r\n" + 
			"SLYfCI7PyrkyufiDmpuGsfwAZbxaoNBW7BD6ywIDAQABAoIBAQCq2lWFIXyPW1xU\r\n" + 
			"vwrAyMAxC1XZpYsHwQd/67GwJI7MpRKwoh8ifSmb6EE+giT7m7S7C0AoSb7Jn4SL\r\n" + 
			"0YazrMWzr93E6qvReMB2cCxNEhEMhiiHHKGao7A6w8C7VfOsbG8G6HW8lo7qB674\r\n" + 
			"7vM12e6W4B/y24L9SodJG6yqoG8VRbpqNUdMkYfo83Yt81sQAgRAnZxQ3vmlRJwS\r\n" + 
			"0ebgtNFTG9hydfH5cjKYmkSYGM+POq71qGMUT6uL5QXm4GpAAjRLsWM+U64vd8TM\r\n" + 
			"/gyqNmH1cHaW5lfV0rPc6Brlbrqq0Lhi4Ah9KTtwr2ciJ3QaR1tm/NLLWJsIvr3t\r\n" + 
			"lzwpgNpJAoGBAOzT472PkA5VmH7opIzVRwPbV/jr0qNXW/5dLSDxS9Rc8L0lVlTC\r\n" + 
			"hcO6+jAAdi1Wp+5I20BUUsSgwPTT+Df5b/ZQigua+Pn5M3gL7INvThJrJ3LAFc04\r\n" + 
			"sNS5WGya8j1VcZmTgwdtemQ6AzVtdca18Uu/FonuWO5/YLQTPkeFRh7nAoGBANmT\r\n" + 
			"+kijgW73eLg51eDWvv2lyMFYF6Yvc8oTrGzK4bjvyQrK2sKK9C9/oJ9zNdXag8FU\r\n" + 
			"V/d/QcLAvSRdDVj1mvL0MME0aDVfjycKSrDswXcJfiU/b0Zp5q9wDers8IbiRWGV\r\n" + 
			"X2hCRFPzI/bM6ZBRHzpU7RtR9WfZJHA1G27fxHx9AoGBAOn2Sj6y3TXMCeMWIbmf\r\n" + 
			"2ZBN2RLopicH3rHjivnIZ4lcZ9f0Ut1Zbpx200MosZcIzM3y2+cpZrsptHYqEkX5\r\n" + 
			"/knwNvBQITeSoO4vmXAfPZmkkkwix2b+YnNEx75DNJyzFI8L05464XayeQkHIhs1\r\n" + 
			"EzQpizKobOLwo7BCcnxlhWhxAoGBAL7sHt/GujlJZuEhvxAA4K9q3nLqfjYRxaU5\r\n" + 
			"BkCg9tl1cBDMWJIFpKhZcfT4//CcQqlO/bHsNSRURKS4EpJRUpqGtJGVDyxmohM7\r\n" + 
			"8La3yjxOVCcTNu2u2AJybYQMTjPOMdujW4j9PT/9Rz22Ke29gY2KIFWs4Y8y98qO\r\n" + 
			"j3gHWz9xAoGAYjyrfDKnQ8+C45NbrnemnTB+pR2xQ8Q6DsG+cUaZnCSzZkQaAJZi\r\n" + 
			"QL4HEK9ZaGSgJZMkxagDzKKF4QSY3LumFj8RYd2p7LvDHsCOKq3hN+4UuhIdA4Va\r\n" + 
			"DqjUr6c3FIIH00CEZaOgTEpCYTknlGFnjTTvgMjxZuelOM4eLgQxCa4=\r\n" + 
			"-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLICA="-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyUiBQZPMXtFI1cPsna2C\r\n" + 
			"srVpYkCyZQfe1XE/K3T/tNdGSvzDh25gnhf2fvbMZcd2YOg1gksp16ImOHAwGtFK\r\n" + 
			"0x2S8og/akGayB8x9/6OOGgZpdElhngmnLas48nun/jpF94mubBNPcPbiYmAVlsJ\r\n" + 
			"gbVS6tCdBWVVDjhAtqoRLEt/DFHoS6fTui0AmWmhqCmuXn0UGyqjYCNpGtqL2LL/\r\n" + 
			"mzsHREG7ym2q9HUEBpu+PLVx+kHLtSo7rWPWxFgnGKMHaRoOCB7zsVjPkR5K852m\r\n" + 
			"RaPlUQwMuB4UVoRN2KN1V+JKaE4ySLYfCI7PyrkyufiDmpuGsfwAZbxaoNBW7BD6\r\n" + 
			"ywIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";

}

