package com.mparticle.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mparticle.sdk.model.audienceprocessing.AudienceMembershipChangeRequest;
import com.mparticle.sdk.model.audienceprocessing.AudienceMembershipChangeResponse;
import com.mparticle.sdk.model.audienceprocessing.AudienceSubscriptionRequest;
import com.mparticle.sdk.model.audienceprocessing.AudienceSubscriptionResponse;
import com.mparticle.sdk.model.eventprocessing.EventProcessingRequest;
import com.mparticle.sdk.model.eventprocessing.EventProcessingResponse;
import com.mparticle.sdk.model.registration.ModuleRegistrationRequest;
import com.mparticle.sdk.model.registration.ModuleRegistrationResponse;

import java.util.UUID;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(name="module_registration_request", value=ModuleRegistrationRequest.class),
        @JsonSubTypes.Type(name="module_registration_response", value=ModuleRegistrationResponse.class),
        @JsonSubTypes.Type(name="event_processing_request", value=EventProcessingRequest.class),
        @JsonSubTypes.Type(name="event_processing_response", value=EventProcessingResponse.class),
        @JsonSubTypes.Type(name="audience_subscription_request", value= AudienceSubscriptionRequest.class),
        @JsonSubTypes.Type(name="audience_subscription_response", value= AudienceSubscriptionResponse.class),
        @JsonSubTypes.Type(name="audience_membership_change_request", value= AudienceMembershipChangeRequest.class),
        @JsonSubTypes.Type(name="audience_membership_change_response", value= AudienceMembershipChangeResponse.class)
})
public abstract class Message {

    private final Type type;

    @JsonProperty(value="id", required=true)
    private final UUID id;

    @JsonProperty(value="timestamp_ms", required=true)
    private long timestamp;

    public Type getType() {
        return type;
    }

    public UUID getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Message(Type type) {
        this.type = type;
        this.id = UUID.randomUUID();
        this.timestamp = System.currentTimeMillis();
    }

    public enum Type {
        MODULE_REGISTRATION_REQUEST,
        MODULE_REGISTRATION_RESPONSE,
        EVENT_PROCESSING_REQUEST,
        EVENT_PROCESSING_RESPONSE,
        AUDIENCE_SUBSCRIPTION_REQUEST,
        AUDIENCE_SUBSCRIPTION_RESPONSE,
        AUDIENCE_MEMBERSHIP_CHANGE_REQUEST,
        AUDIENCE_MEMBERSHIP_CHANGE_RESPONSE;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }
}
