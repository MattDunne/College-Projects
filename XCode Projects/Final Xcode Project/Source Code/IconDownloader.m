/*
 Copyright (C) 2015 Apple Inc. All Rights Reserved.
 See LICENSE.txt for this sample’s licensing information
 
 Abstract:
 Helper object for managing the downloading of a particular app's icon.
 It uses NSURLSession/NSURLSessionDataTask to download the app's icon in the background if it does not
 yet exist and works in conjunction with the RootViewController to manage which apps need their icon.
 */

#import "IconDownloader.h"
#import "News.h"

@interface IconDownloader ()

@property (nonatomic, strong) NSURLSessionDataTask *sessionTask;

@end


#pragma mark -

@implementation IconDownloader

// -------------------------------------------------------------------------------
//    startDownload
// -------------------------------------------------------------------------------
- (void)startDownload
{
    
    NSString *newsImageURL = _newsRecord.newsImageUrl;
    
    NSString *imgURL = nil;
    imgURL = newsImageURL;
    
    
    NSURLRequest *request = [NSURLRequest requestWithURL:[NSURL URLWithString:imgURL]];
    
    // create an session data task to obtain and download the app icon
    _sessionTask = [[NSURLSession sharedSession] dataTaskWithRequest:request
                                                   completionHandler:^(NSData *data, NSURLResponse *response, NSError *error) {
                                                       
                                                       // in case we want to know the response status code
                                                       //NSInteger HTTPStatusCode = [(NSHTTPURLResponse *)response statusCode];
                                                       
                                                       if (error != nil)
                                                       {
                                                           if ([error code] == -1022)
                                                           {
                                                               // if you get error NSURLErrorAppTransportSecurityRequiresSecureConnection (-1022),
                                                               // then your Info.plist has not been properly configured to match the target server.
                                                               //
                                                               abort();
                                                           }
                                                       }
                                                       
                                                       
                                                       [[NSOperationQueue mainQueue] addOperationWithBlock: ^{
                                                           
                                                           // Set appIcon and clear temporary data/image
                                                           UIImage *image = [[UIImage alloc] initWithData:data];
                                                           self.newsRecord.newsImage = image;
                                                           
                                                           // call our completion handler to tell our client that our icon is ready for display
                                                           if (self.completionHandler != nil)
                                                           {
                                                               self.completionHandler();
                                                           }
                                                       }];
                                                   }];
    
    [self.sessionTask resume];
}

// -------------------------------------------------------------------------------
//    cancelDownload
// -------------------------------------------------------------------------------
- (void)cancelDownload
{
    [self.sessionTask cancel];
    _sessionTask = nil;
}

@end

