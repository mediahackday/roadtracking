package com.roadtracking.web.security.oauth.model;

import java.util.List;

public class Profile {
		private List<ProfileMail> emails;

		public Profile() {
		}

		public List<ProfileMail> getEmails() {
			return emails;
		}

		public void setEmails(List<ProfileMail> emails) {
			this.emails = emails;
		}
	}