//
//  RLPhotoManipulate.m
//  RecLoc
//
//  Created by Paril Shah on 4/14/17.
//  Copyright © 2017 Paril Shah. All rights reserved.
//

#import "RLPhotoManipulate.h"
@import AWSRekognition;

@interface RLPhotoManipulate()
@property(nonatomic, copy) void (^completionBlock)(NSArray *arry, NSError *error);
@end

@implementation RLPhotoManipulate

- (instancetype)initWithBlock:(AnalyzeImageWithResponse)requestImageBlock{
    self = [super init];
    self.completionBlock = requestImageBlock;
    return self;
}

- (void)returnDictionary:(UIImage *)image{
    AWSCognitoCredentialsProvider *credentialsProvider = [[AWSCognitoCredentialsProvider alloc]initWithRegionType:AWSRegionUSWest2 identityPoolId:@"us-west-2:d54d4def-4b9e-4e4f-93f9-c6aba093e411"];
    
    AWSServiceConfiguration *configuration = [[AWSServiceConfiguration alloc]initWithRegion:AWSRegionUSWest2 credentialsProvider:credentialsProvider];
    AWSServiceManager.defaultServiceManager.defaultServiceConfiguration = configuration;
    [AWSRekognition registerRekognitionWithConfiguration:configuration forKey:@"USWest2Rekognition"];
    AWSRekognition *Rekognition = [AWSRekognition RekognitionForKey:@"USWest2Rekognition"];
    
    AWSRekognitionImage *im = [AWSRekognitionImage new];
    im.bytes = UIImageJPEGRepresentation(image, 0.7);
    
    AWSRekognitionDetectLabelsRequest *request = [AWSRekognitionDetectLabelsRequest new];
    
    request.image = im;
    request.maxLabels = [NSNumber numberWithInt:10];
    request.minConfidence = [NSNumber numberWithInt:60];
    
    [Rekognition detectLabels:request completionHandler:^(AWSRekognitionDetectLabelsResponse * _Nullable response, NSError * _Nullable error) {
        NSLog(@"%@",response.labels);
        self.completionBlock(response.labels, error);
    }];
}
@end
