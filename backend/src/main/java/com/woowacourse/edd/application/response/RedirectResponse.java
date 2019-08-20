package com.woowacourse.edd.application.response;

public class RedirectResponse {
    private final String redirectUrl;
    private final Long userId;

    public RedirectResponse(String redirectUrl, Long userId) {
        this.redirectUrl = redirectUrl;
        this.userId = userId;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public Long getUserId() {
        return userId;
    }
}
