package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DropboxAccount {
    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("name")
    private Name name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("email_verified")
    private boolean emailVerified;

    @JsonProperty("profile_photo_url")
    private String profilePhotoUrl;

    @JsonProperty("disabled")
    private boolean disabled;

    @JsonProperty("country")
    private String country;

    @JsonProperty("locale")
    private String locale;

    @JsonProperty("referral_link")
    private String referralLink;

    @JsonProperty("is_paired")
    private boolean isPaired;

    @JsonProperty("account_type")
    private accountType accountType;

    @JsonProperty("root_info")
    private RootInfo rootInfo;

    public DropboxAccount() {
    }

    public DropboxAccount(String accountId, Name name, String email, boolean emailVerified, String profilePhotoUrl, boolean disabled, String country, String locale, String referralLink, boolean isPaired, accountType accountType, RootInfo rootInfo) {
        this.accountId = accountId;
        this.name = name;
        this.email = email;
        this.emailVerified = emailVerified;
        this.profilePhotoUrl = profilePhotoUrl;
        this.disabled = disabled;
        this.country = country;
        this.locale = locale;
        this.referralLink = referralLink;
        this.isPaired = isPaired;
        this.accountType = accountType;
        this.rootInfo = rootInfo;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getProfile_photo_url() {
        return profilePhotoUrl;
    }

    public void setProfile_photo_url(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getReferralLink() {
        return referralLink;
    }

    public void setReferralLink(String referralLink) {
        this.referralLink = referralLink;
    }

    public boolean isPaired() {
        return isPaired;
    }

    public void setPaired(boolean paired) {
        isPaired = paired;
    }

    public accountType getAccountType() {
        return accountType;
    }

    public void setAccountType(accountType accountType) {
        this.accountType = accountType;
    }

    public RootInfo getRootInfo() {
        return rootInfo;
    }

    public void setRootInfo(RootInfo rootInfo) {
        this.rootInfo = rootInfo;
    }

    @Override
    public String toString() {
        return "User ID: " + accountId + "\n" +
                "Display name: " + name.getDisplayName() + "\n" +
                "Name: " + name.getGivenName() + " " + name.getSurname() + " (" + name.getFamiliarName() + ")\n" +
                "E-mail: " + email + " (" + (emailVerified ? "verified)" : "not verified)") + ")\n" +
                "Country: " + country + "\n" +
                "Referral link: " + referralLink;
    }
}
