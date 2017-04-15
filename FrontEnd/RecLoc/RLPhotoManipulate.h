//
//  RLPhotoManipulate.h
//  RecLoc
//
//  Created by Paril Shah on 4/14/17.
//  Copyright © 2017 Paril Shah. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

typedef void (^AnalyzeImageWithResponse)(NSArray *, NSError *);
@interface RLPhotoManipulate : NSObject
- (void)returnDictionary:(UIImage *)image;
- (instancetype)initWithBlock:(AnalyzeImageWithResponse)requestImageBlock;
@end
