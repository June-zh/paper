package com.june.papcenter.service;

import java.util.Map;

public interface MsmService {
    boolean send(Map<String, Object> param, String email);
}
