package com.ssafy.ebangjbang.model;

import java.util.HashMap;
import java.util.Map;

public class KakaoProfile {
	
	public class KakaoAccount {
		
		public class Profile {

			private String nickname;
			private Map<String, Object> additional_properties = new HashMap<String, Object>();

			public String getNickname() {
				return nickname;
			}

			public void setNickname(String nickname) {
			this.nickname = nickname;
			}

			public Map<String, Object> getAdditionalProperties() {
			return this.additional_properties;
			}

			public void setAdditionalProperty(String name, Object value) {
			this.additional_properties.put(name, value);
			}

		}
		
		private Boolean profile_nickname_needs_agreement;
		private Profile profile;
		private Boolean has_email;
		private Boolean email_needs_agreement;
		private Boolean is_email_valid;
		private Boolean is_email_verified;
		private String email;
		private Map<String, Object> additional_properties = new HashMap<String, Object>();

		public Boolean getProfile_nickname_needs_agreement() {
			return profile_nickname_needs_agreement;
		}

		public void Profile_nickname_needs_agreement(Boolean profileNicknameNeedsAgreement) {
			this.profile_nickname_needs_agreement = profileNicknameNeedsAgreement;
		}

		public Profile getProfile() {
			return profile;
		}

		public void setProfile(Profile profile) {
			this.profile = profile;
		}

		public Boolean getHas_email() {
			return has_email;
		}

		public void setHas_email(Boolean hasEmail) {
			this.has_email = hasEmail;
		}

		public Boolean getEmail_needs_agreement() {
			return email_needs_agreement;
		}

		public void setEmail_needs_agreement(Boolean emailNeedsAgreement) {
			this.email_needs_agreement = emailNeedsAgreement;
		}

		public Map<String, Object> getAdditional_properties() {
			return this.additional_properties;
		}

		public void setAdditional_property(String name, Object value) {
			this.additional_properties.put(name, value);
		}

		public Boolean getIs_email_valid() {
			return is_email_valid;
		}

		public void setIs_email_valid(Boolean is_email_valid) {
			this.is_email_valid = is_email_valid;
		}

		public Boolean getIs_email_verified() {
			return is_email_verified;
		}

		public void setIs_email_verified(Boolean is_email_verified) {
			this.is_email_verified = is_email_verified;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
		
	}
	
	
	public class Properties {

		private String nickname;
		private Map<String, Object> additional_properties = new HashMap<String, Object>();

		public String getNickname() {
		return nickname;
		}

		public void setNickname(String nickname) {
		this.nickname = nickname;
		}

		public Map<String, Object> getAdditional_properties() {
		return this.additional_properties;
		}

		public void setAdditional_property(String name, Object value) {
		this.additional_properties.put(name, value);
		}

	}
	
	private Integer id;
	private String connected_at;
	private Properties properties;
	private KakaoAccount kakao_account;
	private Map<String, Object> additional_properties = new HashMap<String, Object>();

	public Integer getId() {
	return id;
	}

	public void setId(Integer id) {
	this.id = id;
	}

	public String getConnected_at() {
	return connected_at;
	}

	public void setConnected_at(String connected_at) {
	this.connected_at = connected_at;
	}

	public Properties getProperties() {
	return properties;
	}

	public void setProperties(Properties properties) {
	this.properties = properties;
	}

	public KakaoAccount getKakao_account() {
	return kakao_account;
	}

	public void setKakao_account(KakaoAccount kakaoAccount) {
	this.kakao_account = kakaoAccount;
	}

	public Map<String, Object> getAdditional_properties() {
	return this.additional_properties;
	}

	public void setAdditional_properties(String name, Object value) {
	this.additional_properties.put(name, value);
	}
}

